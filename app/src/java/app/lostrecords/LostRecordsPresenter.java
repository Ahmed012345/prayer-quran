package com.zaker.android.sapeh.app.lostrecords;

import com.zaker.android.sapeh.AppConstants;
import com.zaker.android.sapeh.BackgroundQueue;
import com.zaker.android.sapeh.app.info.RecordInfo;
import com.zaker.android.sapeh.data.Prefs;
import com.zaker.android.sapeh.data.database.LocalRepository;
import com.zaker.android.sapeh.data.database.OnRecordsLostListener;
import com.zaker.android.sapeh.data.database.Record;
import com.zaker.android.sapeh.util.AndroidUtils;
import com.zaker.android.sapeh.util.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 14.12.2019.
 * @author Dimowner
 */
public class LostRecordsPresenter implements LostRecordsContract.UserActionsListener {

	private LostRecordsContract.View view;
	private final BackgroundQueue loadingTasks;
	private final BackgroundQueue recordingsTasks;
	private final LocalRepository localRepository;
	private final Prefs prefs;

	public LostRecordsPresenter(BackgroundQueue loadingTasks, BackgroundQueue recordingsTasks,
										 LocalRepository localRepository, Prefs prefs) {
		this.loadingTasks = loadingTasks;
		this.recordingsTasks = recordingsTasks;
		this.localRepository = localRepository;
		this.prefs = prefs;
	}

	@Override
	public void bindView(LostRecordsContract.View v) {
		this.view = v;

		localRepository.setOnRecordsLostListener(new OnRecordsLostListener() {
			@Override
			public void onLostRecords(final List<Record> lostRecords) {
				AndroidUtils.runOnUIThread(new Runnable() {
					@Override
					public void run() {
						ArrayList<RecordItem> list = new ArrayList<>();
						for (Record r : lostRecords) {
							list.add(new RecordItem(r.getId(), r.getName(), r.getDuration(), r.getPath(), r.getCreated()));
						}
						if (view != null) {
							if (list.isEmpty()) {
								view.showEmpty();
							} else {
								view.showLostRecords(list);
								view.hideEmpty();
							}
						}
					}
				});
			}
		});
		loadingTasks.postRunnable(new Runnable() {
			@Override
			public void run() {
				localRepository.getAllRecords();
			}
		});
	}

	@Override
	public void unbindView() {
		this.localRepository.setOnRecordsLostListener(null);
		this.view = null;
	}

	@Override
	public void clear() {
		unbindView();
	}

	@Override
	public void deleteRecords(final List<RecordItem> list) {
		recordingsTasks.postRunnable(new Runnable() {
			@Override
			public void run() {
				for (RecordItem rec : list) {
					localRepository.deleteRecord(rec.getId());
//					fileRepository.deleteRecordFile(rec.getPath());
					if (prefs.getActiveRecord() == rec.getId()) {
						prefs.setActiveRecord(-1);
					}
				}
				AndroidUtils.runOnUIThread(new Runnable() {
					@Override
					public void run() {
						if (view != null) {
							view.showEmpty();
						}
					}
				});
			}
		});
	}

	@Override
	public void onRecordInfo(String name, long duration, String location, long created) {
		String format;
		if (location.contains(AppConstants.MP3_EXTENSION)) {
			format = AppConstants.MP3_EXTENSION;
		} else if (location.contains(AppConstants.WAV_EXTENSION)) {
			format = AppConstants.WAV_EXTENSION;
		} else {
			format = "";
		}
		view.showRecordInfo(new RecordInfo(FileUtil.removeFileExtension(name), format, duration/1000, new File(location).length(), location, created));
	}

	@Override
	public void deleteRecord(final RecordItem rec) {
		recordingsTasks.postRunnable(new Runnable() {
			@Override
			public void run() {
				localRepository.deleteRecord(rec.getId());
//				fileRepository.deleteRecordFile(rec.getPath());
				if (prefs.getActiveRecord() == rec.getId()) {
					prefs.setActiveRecord(-1);
				}
				AndroidUtils.runOnUIThread(new Runnable() {
					@Override
					public void run() {
						if (view != null) {
							view.onDeletedRecord(rec.getId());
						}
					}
				});
			}
		});
	}

}
