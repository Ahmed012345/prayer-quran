/*
 * Copyright 2020 Dmitriy Ponomarenko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zaker.android.sapeh.app;

import android.os.Build;

import com.zaker.android.sapeh.ARApplication;
import com.zaker.android.sapeh.AppConstants;
import com.zaker.android.sapeh.BackgroundQueue;
import com.zaker.android.sapeh.IntArrayList;
import com.zaker.android.sapeh.audio.AudioDecoder;
import com.zaker.android.sapeh.audio.recorder.RecorderContract;
import com.zaker.android.sapeh.data.Prefs;
import com.zaker.android.sapeh.data.database.LocalRepository;
import com.zaker.android.sapeh.data.database.Record;
import com.zaker.android.sapeh.exception.AppException;
import com.zaker.android.sapeh.util.AndroidUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class AppRecorderImpl implements AppRecorder {

	private RecorderContract.Recorder audioRecorder;
	private final BackgroundQueue recordingsTasks;

	private final BackgroundQueue processingTasks;
	private final LocalRepository localRepository;
	private final RecorderContract.RecorderCallback recorderCallback;
	private final List<AppRecorderCallback> appCallbacks;
	private final Prefs prefs;
	private IntArrayList recordingData;
	private long recordingDuration;
	private boolean isProcessing = false;

	private volatile static AppRecorderImpl instance;

	public static AppRecorderImpl getInstance(RecorderContract.Recorder recorder,
															LocalRepository localRep, BackgroundQueue tasks,
															BackgroundQueue processingTasks, Prefs prefs) {
		if (instance == null) {
			synchronized (AppRecorderImpl.class) {
				if (instance == null) {
					instance = new AppRecorderImpl(recorder, localRep, tasks, processingTasks, prefs);
				}
			}
		}
		return instance;
	}

	private AppRecorderImpl(RecorderContract.Recorder recorder,
									LocalRepository localRep, BackgroundQueue tasks,
									final BackgroundQueue processingTasks, Prefs pr) {
		this.audioRecorder = recorder;
		this.localRepository = localRep;
		this.recordingsTasks = tasks;
		this.processingTasks = processingTasks;
		this.prefs = pr;
		this.appCallbacks = new ArrayList<>();
		this.recordingData = new IntArrayList();

		recorderCallback = new RecorderContract.RecorderCallback() {
			@Override
			public void onPrepareRecord() {
				audioRecorder.startRecording();
			}

			@Override
			public void onStartRecord(File output) {
				recordingDuration = 0;
				onRecordingStarted(output);
			}

			@Override
			public void onPauseRecord() {
				onRecordingPaused();
			}

			@Override
			public void onRecordProgress(final long mills, final int amplitude) {
				recordingDuration = mills;
				onRecordingProgress(mills, amplitude);
				recordingData.add(amplitude);
			}

			@Override
			public void onStopRecord(final File output) {
				recordingDuration = 0;
				recordingsTasks.postRunnable(new Runnable() {
					@Override
					public void run() {
						long duration = AndroidUtils.readRecordDuration(output);
						int[] waveForm = convertRecordingData(recordingData, (int) (duration / 1000000f));
						final Record record = localRepository.getRecord((int) prefs.getActiveRecord());
						final Record update = new Record(
								record.getId(),
								record.getName(),
								duration,
								record.getCreated(),
								record.getAdded(),
								record.getRemoved(),
								record.getPath(),
								record.isBookmarked(),
								record.isWaveformProcessed(),
								waveForm);
						if (localRepository.updateRecord(update)) {
							recordingData.clear();
							final Record rec = localRepository.getRecord(update.getId());
							AndroidUtils.runOnUIThread(new Runnable() {
								@Override
								public void run() {
									onRecordingStopped(output, rec);
								}
							});
							decodeRecordWaveform(rec);
						} else {
							//Try to update record again if failed.
							if (localRepository.updateRecord(update)) {
								recordingData.clear();
								final Record rec = localRepository.getRecord(update.getId());
								AndroidUtils.runOnUIThread(new Runnable() {
									@Override
									public void run() {
										onRecordingStopped(output, rec);
									}
								});
								decodeRecordWaveform(rec);
							} else {
								onRecordingStopped(output, record);
							}
						}
					}
				});
			}

			@Override
			public void onError(AppException e) {
				Timber.e(e);
				onRecordingError(e);
			}
		};
		audioRecorder.setRecorderCallback(recorderCallback);
	}

	private int[] convertRecordingData(IntArrayList list, int durationSec) {
		if (durationSec > AppConstants.LONG_RECORD_THRESHOLD_SECONDS) {
			int sampleCount = ARApplication.getLongWaveformSampleCount();
			int[] waveForm = new int[sampleCount];
			if (list.size() < sampleCount*2) {
				float scale = (float) list.size() / (float) sampleCount;
				for (int i = 0; i < sampleCount; i++) {
					waveForm[i] = convertAmp(list.get((int) Math.floor(i*scale)));
				}
			} else {
				float scale = (float) list.size() / (float) sampleCount;
				for (int i = 0; i < sampleCount; i++) {
					int val = 0;
					int step = (int) Math.ceil(scale);
					for (int j = 0; j < step; j++) {
						val += list.get((int)(i * scale + j));
					}
					val = (int) ((float) val / scale);
					waveForm[i] = convertAmp(val);
				}
			}
			return waveForm;
		} else {
			int[] waveForm = new int[list.size()];
			for (int i = 0; i < list.size(); i++) {
				waveForm[i] = convertAmp(list.get(i));
			}
			return waveForm;
		}
	}

	/**
	 * Convert dB amp value to view amp.
	 */
	private int convertAmp(double amp) {
		return (int)(255*(amp/32767f));
	}

	@Override
	public void decodeRecordWaveform(final Record decRec) {
		processingTasks.postRunnable(new Runnable() {
			@Override
			public void run() {
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
					isProcessing = true;
					final String path = decRec.getPath();
					if (path != null && !path.isEmpty()) {
						AudioDecoder.decode(path, new AudioDecoder.DecodeListener() {
							@Override
							public void onStartDecode(long duration, int channelsCount, int sampleRate) {
								decRec.setDuration(duration);
								AndroidUtils.runOnUIThread(new Runnable() {
									@Override
									public void run() {
										onRecordProcessing();
									}
								});
							}

							@Override
							public void onFinishDecode(int[] data, long duration) {
								final Record rec = new Record(
										decRec.getId(),
										decRec.getName(),
										decRec.getDuration(),
										decRec.getCreated(),
										decRec.getAdded(),
										decRec.getRemoved(),
										decRec.getPath(),
										decRec.isBookmarked(),
										true,
										data);
								localRepository.updateRecord(rec);
								isProcessing = false;
								AndroidUtils.runOnUIThread(new Runnable() {
									@Override
									public void run() {
										onRecordFinishProcessing();
									}
								});
							}

							@Override
							public void onError(Exception exception) {
								isProcessing = false;
							}
						});
					} else {
						isProcessing = false;
						Timber.e("File path is null or empty");
					}
				}
			}
		});
	}

	@Override
	public void addRecordingCallback(AppRecorderCallback callback) {
		appCallbacks.add(callback);
	}

	@Override
	public void removeRecordingCallback(AppRecorderCallback callback) {
		appCallbacks.remove(callback);
	}

	@Override
	public void setRecorder(RecorderContract.Recorder recorder) {
		this.audioRecorder = recorder;
		this.audioRecorder.setRecorderCallback(recorderCallback);
	}

	@Override
	public void startRecording(String filePath, int channelCount, int sampleRate, int bitrate) {
		if (!audioRecorder.isRecording()) {
			audioRecorder.prepare(filePath, channelCount, sampleRate, bitrate);
		}
	}

	@Override
	public void pauseRecording() {
		if (audioRecorder.isRecording()) {
			audioRecorder.pauseRecording();
		}
	}

	@Override
	public void resumeRecording() {
		if (audioRecorder.isPaused()) {
			audioRecorder.startRecording();
		}
	}

	@Override
	public void stopRecording() {
		if (audioRecorder.isRecording()) {
			audioRecorder.stopRecording();
		}
	}

	@Override
	public IntArrayList getRecordingData() {
		return recordingData;
	}

	@Override
	public long getRecordingDuration() {
		return recordingDuration;
	}

	@Override
	public boolean isRecording() {
		return audioRecorder.isRecording();
	}

	@Override
	public boolean isPaused() {
		return audioRecorder.isPaused();
	}

	@Override
	public void release() {
		recordingData.clear();
		audioRecorder.stopRecording();
		appCallbacks.clear();
	}

	@Override
	public boolean isProcessing() {
		return isProcessing;
	}

	private void onRecordingStarted(File output) {
		if (!appCallbacks.isEmpty()) {
			for (int i = 0; i < appCallbacks.size(); i++) {
				appCallbacks.get(i).onRecordingStarted(output);
			}
		}
	}

	private void onRecordingPaused() {
		if (!appCallbacks.isEmpty()) {
			for (int i = 0; i < appCallbacks.size(); i++) {
				appCallbacks.get(i).onRecordingPaused();
			}
		}
	}

	private void onRecordProcessing() {
		isProcessing = true;
		if (!appCallbacks.isEmpty()) {
			for (int i = 0; i < appCallbacks.size(); i++) {
				appCallbacks.get(i).onRecordProcessing();
			}
		}
	}

	private void onRecordFinishProcessing() {
		isProcessing = false;
		if (!appCallbacks.isEmpty()) {
			for (int i = 0; i < appCallbacks.size(); i++) {
				appCallbacks.get(i).onRecordFinishProcessing();
			}
		}
	}

	private void onRecordingStopped(File file, Record record) {
		if (!appCallbacks.isEmpty()) {
			for (int i = 0; i < appCallbacks.size(); i++) {
				appCallbacks.get(i).onRecordingStopped(file, record);
			}
		}
	}

	private void onRecordingProgress(long mills, int amp) {
		if (!appCallbacks.isEmpty()) {
			for (int i = 0; i < appCallbacks.size(); i++) {
				appCallbacks.get(i).onRecordingProgress(mills, amp);
			}
		}
	}

	private void onRecordingError(AppException e) {
		if (!appCallbacks.isEmpty()) {
			for (int i = 0; i < appCallbacks.size(); i++) {
				appCallbacks.get(i).onError(e);
			}
		}
	}
}
