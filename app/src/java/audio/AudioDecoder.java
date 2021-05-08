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

package com.zaker.android.sapeh.audio;

import android.annotation.SuppressLint;
import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.Build;


import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.util.Arrays;

import androidx.annotation.NonNull;

import com.zaker.android.sapeh.ARApplication;
import com.zaker.android.sapeh.AppConstants;
import com.zaker.android.sapeh.IntArrayList;

import timber.log.Timber;

/**
 * Created on 14.03.2020.
 * @author Dimowner
 */
public class AudioDecoder {
	private static final int QUEUE_INPUT_BUFFER_EFFECTIVE = 1; // Most effective and fastest
	private static final int QUEUE_INPUT_BUFFER_SIMPLE = 2;	// Less effective and slower

	private float dpPerSec = AppConstants.SHORT_RECORD_DP_PER_SECOND;

	private int sampleRate;
	private int channelCount;
	private int[] oneFrameAmps;
	private int frameIndex = 0;

	private long duration;
	private static final String[] SUPPORTED_EXT = new String[]{"mp3", "wav", "3gpp", "3gp", "amr", "aac", "m4a", "mp4", "ogg", "flac"};

	private IntArrayList gains;
	

	public interface DecodeListener {
		void onStartDecode(long duration, int channelsCount, int sampleRate);
		void onFinishDecode(int[] data, long duration);
		void onError(Exception exception);
	}

	private AudioDecoder() {
	}

	public static void decode(@NonNull String fileName, @NonNull DecodeListener decodeListener) {
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				throw new java.io.FileNotFoundException(fileName);
			}
			String name = file.getName().toLowerCase();
			String[] components = name.split("\\.");
			if (components.length < 2) {
				throw new IOException();
			}
			if (!Arrays.asList(SUPPORTED_EXT).contains(components[components.length - 1])) {
				throw new IOException();
			}
			AudioDecoder decoder = new AudioDecoder();
			decoder.decodeFile(file, decodeListener, QUEUE_INPUT_BUFFER_EFFECTIVE);
		} catch (Exception e) {
			decodeListener.onError(e);
		}
	}


	private int calculateSamplesPerFrame() {
		return (int)(sampleRate / dpPerSec);
	}


	private void decodeFile(@NonNull final File mInputFile, @NonNull final DecodeListener decodeListener, final int queueType)
			throws IOException, OutOfMemoryError, IllegalStateException {
		gains = new IntArrayList();
		final MediaExtractor extractor = new MediaExtractor();
		MediaFormat format = null;
		int i;

		extractor.setDataSource(mInputFile.getPath());
		int numTracks = extractor.getTrackCount();
		// find and select the first audio track present in the file.
		for (i = 0; i < numTracks; i++) {
			format = extractor.getTrackFormat(i);
			if (format.getString(MediaFormat.KEY_MIME).startsWith("audio/")) {
				extractor.selectTrack(i);
				break;
			}
		}

		if (i == numTracks || format == null) {
			throw new IOException("No audio track found in " + mInputFile.toString());
		}
		channelCount = format.getInteger(MediaFormat.KEY_CHANNEL_COUNT);
		sampleRate = format.getInteger(MediaFormat.KEY_SAMPLE_RATE);

			//SoundFile duration.
			duration = format.getLong(MediaFormat.KEY_DURATION);
	


		//TODO: Make waveform independent from dpPerSec!!!
		dpPerSec = ARApplication.getDpPerSecond((float) duration/1000000f);
		oneFrameAmps = new int[calculateSamplesPerFrame() * channelCount];

		String mimeType = format.getString(MediaFormat.KEY_MIME);


		//Start decoding
		MediaCodec decoder = MediaCodec.createDecoderByType(mimeType);

		decodeListener.onStartDecode(duration, channelCount, sampleRate);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			decoder.setCallback(new MediaCodec.Callback() {

				private boolean mOutputEOS = false;
				private boolean mInputEOS = false;

				@Override
				public void onError(@NonNull MediaCodec codec, @NonNull MediaCodec.CodecException exception) {
					Timber.e(exception);
					if (queueType == QUEUE_INPUT_BUFFER_EFFECTIVE) {
						try {
							AudioDecoder decoder = new AudioDecoder();
							decoder.decodeFile(mInputFile, decodeListener, QUEUE_INPUT_BUFFER_SIMPLE);
						} catch (IllegalStateException | IOException | OutOfMemoryError e) {
							decodeListener.onError(exception);
						}
					} else {
						decodeListener.onError(exception);
					}
				}

				@Override
				public void onOutputFormatChanged(@NonNull MediaCodec codec, @NonNull MediaFormat format) {
				}

				@Override
				public void onInputBufferAvailable(@NonNull MediaCodec codec, int index) {
					if (mOutputEOS | mInputEOS) return;
					try {
						ByteBuffer inputBuffer;
						inputBuffer = codec.getInputBuffer(index);
						if (inputBuffer == null) return;
						long sampleTime = 0;
						int result;
						if (queueType == QUEUE_INPUT_BUFFER_EFFECTIVE) {
							int total = 0;
							boolean advanced = false;
							int maxresult = 0;
							do {
								result = extractor.readSampleData(inputBuffer, total);
								if (result >= 0) {
									total += result;
									sampleTime = extractor.getSampleTime();
									advanced = extractor.advance();
									maxresult = Math.max(maxresult, result);
								}
							} while (result >= 0 && total < maxresult * 5 && advanced && inputBuffer.capacity() - inputBuffer.limit() > maxresult*3);//3 it is just for insurance. When remove it crash happens. it is ok if replace it by 2 number.
							if (advanced) {
								codec.queueInputBuffer(index, 0, total, sampleTime, 0);
							} else {
								codec.queueInputBuffer(index, 0, 0, -1, MediaCodec.BUFFER_FLAG_END_OF_STREAM);
								mInputEOS = true;
							}
						} else {
							//If QUEUE_INPUT_BUFFER_EFFECTIVE failed then trying this way.
							result = extractor.readSampleData(inputBuffer, 0);
							if (result >= 0) {
								sampleTime = extractor.getSampleTime();
								codec.queueInputBuffer(index, 0, result, sampleTime, 0);
								extractor.advance();
							} else {
								codec.queueInputBuffer(index, 0, 0, -1, MediaCodec.BUFFER_FLAG_END_OF_STREAM);
								mInputEOS = true;
							}
						}
					} catch (IllegalStateException | IllegalArgumentException e) {
						Timber.e(e);
					}
				}

				@Override
				public void onOutputBufferAvailable(@NonNull MediaCodec codec, int index, @NonNull MediaCodec.BufferInfo info) {
					ByteBuffer outputBuffer = codec.getOutputBuffer(index);
					if (outputBuffer != null) {
						outputBuffer.rewind();
						outputBuffer.order(ByteOrder.LITTLE_ENDIAN);
						while (outputBuffer.remaining() > 0) {
							oneFrameAmps[frameIndex] = outputBuffer.getShort();
							frameIndex++;
							if (frameIndex >= oneFrameAmps.length - 1) {
								int j;
								int gain, value;
								gain = -1;
								for (j = 0; j < oneFrameAmps.length; j += channelCount) {
									value = 0;
									for (int k = 0; k < channelCount; k++) {
										value += oneFrameAmps[j + k];
									}
									value /= channelCount;
									if (gain < value) {
										gain = value;
									}
								}
								gains.add((int) Math.sqrt(gain));
								frameIndex = 0;
							}
						}
					}

					mOutputEOS |= ((info.flags & MediaCodec.BUFFER_FLAG_END_OF_STREAM) != 0);
					codec.releaseOutputBuffer(index, false);

					if (mOutputEOS) {
						decodeListener.onFinishDecode(gains.getData(), duration);
						codec.stop();
						codec.release();
						extractor.release();
					}
				}
			});
		}
		decoder.configure(format, null, null, 0);
		decoder.start();
	}
}
