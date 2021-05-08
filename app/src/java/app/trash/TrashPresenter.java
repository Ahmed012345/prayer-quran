package com.zaker.android.sapeh.app.trash;

import com.zaker.android.sapeh.AppConstants;
import com.zaker.android.sapeh.BackgroundQueue;
import com.zaker.android.sapeh.R;
import com.zaker.android.sapeh.app.info.RecordInfo;
import com.zaker.android.sapeh.app.lostrecords.RecordItem;
import com.zaker.android.sapeh.data.FileRepository;
import com.zaker.android.sapeh.data.database.LocalRepository;
import com.zaker.android.sapeh.data.database.Record;
import com.zaker.android.sapeh.util.AndroidUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 15.12.2019.
 * @author Dimowner
 */
public class TrashPresenter implements TrashContract.UserActionsListener {

	private TrashContract.View view;
	private final BackgroundQueue loadingTasks;
	private final BackgroundQueue recordingsTasks;
	private final FileRepository fileRepository;
	private final LocalRepository localRepository;

	public TrashPresenter(BackgroundQueue loadingTasks, BackgroundQueue recordingsTasks,
								 FileRepository fileRepository, LocalRepository localRepository) {
		this.loadingTasks = loadingTasks;
		this.recordingsTasks = recordingsTasks;
		this.fileRepository = fileRepository;
		this.localRepository = localRepository;
	}

	@Override
	public void bindView(final TrashContract.View v) {
		this.view = v;

		loadingTasks.postRunnable(new Runnable() {
			@Override public void run() {
				List<Record> records = localRepository.getTrashRecords();
				final ArrayList<RecordItem> list = new ArrayList<>();
				for (Record r : records) {
					list.add(new RecordItem(r.getId(), r.getName(), r.getDuration(), r.getPath(), r.getCreated()));
				}
				AndroidUtils.runOnUIThread(new Runnable() {
					@Override
					public void run() {
						if (list.isEmpty()) {
							view.showEmpty();
						} else {
							view.showRecords(list);
							view.hideEmpty();
						}
					}
				});
			}
		});
	}

	@Override
	public void unbindView() {
		this.view = null;
	}

	@Override
	public void clear() {
		unbindView();
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
		view.showRecordInfo(new RecordInfo(name, format, duration, new File(location).length(), location, created));
	}

	@Override
	public void deleteRecordFromTrash(final int id, final String path) {
		recordingsTasks.postRunnable(new Runnable() {
			@Override
			public void run() {
				localRepository.removeFromTrash(id);
				fileRepository.deleteRecordFile(path);
				AndroidUtils.runOnUIThread(new Runnable() {
					@Override
					public void run() {
						if (view != null) {
							view.showMessage(R.string.record_deleted_successfully);
							view.recordDeleted(id);
						}
					}
				});
			}
		});
	}

	@Override
	public void deleteAllRecordsFromTrash() {
		recordingsTasks.postRunnable(new Runnable() {
			@Override
			public void run() {
				List<Record> records  = localRepository.getTrashRecords();
				for (int i = 0; i < records.size(); i++) {
					fileRepository.deleteRecordFile(records.get(i).getPath());
				}
				localRepository.emptyTrash();
				AndroidUtils.runOnUIThread(new Runnable() {
					@Override
					public void run() {
						if (view != null) {
							view.showMessage(R.string.all_records_deleted_successfully);
							view.allRecordsRemoved();
						}
					}
				});
			}
		});
	}

	@Override
	public void restoreRecordFromTrash(final int id) {
		recordingsTasks.postRunnable(new Runnable() {
			@Override
			public void run() {
				localRepository.restoreFromTrash(id);
				AndroidUtils.runOnUIThread(new Runnable() {
					@Override
					public void run() {
						if (view != null) {
							view.showMessage(R.string.record_restored_successfully);
							view.recordRestored(id);
						}
					}
				});
			}
		});
	}
}
