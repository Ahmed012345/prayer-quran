/*
 * Copyright 2018 Dmitriy Ponomarenko
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

package com.zaker.android.sapeh.app.records;

import android.Manifest;
import android.animation.Animator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.jem.rubberpicker.RubberSeekBar;
import com.marcoscg.materialtoast.MaterialToast;
import com.realpacific.clickshrinkeffect.ClickShrinkEffect;
import com.zaker.android.sapeh.ARApplication;
import com.zaker.android.sapeh.AppConstants;
import com.zaker.android.sapeh.ColorMap;
import com.zaker.android.sapeh.R;
import com.zaker.android.sapeh.app.DownloadService;
import com.zaker.android.sapeh.app.PlaybackService;
import com.zaker.android.sapeh.app.info.ActivityInformation;
import com.zaker.android.sapeh.app.info.RecordInfo;
import com.zaker.android.sapeh.app.main.sweetalert.SweetAlertDialog;
import com.zaker.android.sapeh.app.trash.TrashActivity;
import com.zaker.android.sapeh.app.widget.SimpleWaveformView;
import com.zaker.android.sapeh.app.widget.TouchLayout;
import com.zaker.android.sapeh.app.widget.WaveformView;
import com.zaker.android.sapeh.data.database.Record;
import com.zaker.android.sapeh.util.AndroidUtils;
import com.zaker.android.sapeh.util.AnimationUtil;
import com.zaker.android.sapeh.util.FileUtil;
import com.zaker.android.sapeh.util.TimeUtils;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class RecordsActivity extends LocalizationActivity implements RecordsContract.View, View.OnClickListener {

	public static final int REQ_CODE_READ_EXTERNAL_STORAGE_PLAYBACK = 406;

	private RecyclerView recyclerView;
	private LinearLayoutManager layoutManager;
	private RecordsAdapter adapter;

	private LinearLayout toolbar;
	private ProgressBar progressBar;
	private View bottomDivider;
	private ImageButton btnPlay;
	private ImageButton btnStop;
	private ImageButton btnNext;
	private ImageButton btnPrev;
	private ImageButton btnDelete;
	private ImageButton btnBookmarks;
	private ImageButton btnSort;
	private ImageButton btnCheckBookmark;
	private TextView txtProgress;
	private TextView txtDuration;
	private TextView txtName;
	private TextView txtEmpty;
	private TextView txtTitle;
	private ImageView image_toolbar;
	private TouchLayout touchLayout;
	private WaveformView waveformView;
	private ProgressBar panelProgress;
	private RubberSeekBar playProgress;

	private RecordsContract.UserActionsListener presenter;
	private ColorMap colorMap;

	public static Intent getStartIntent(Context context) {
		Intent intent = new Intent(context, RecordsActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		return intent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		colorMap = ARApplication.getInjector().provideColorMap();
		setTheme(colorMap.getAppThemeResource());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_records);

		toolbar = findViewById(R.id.toolbar);
		AndroidUtils.setTranslucent(this, true);

		ImageButton btnBack = findViewById(R.id.btn_back);
		btnBack.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View view) {
				finish();
				ARApplication.getInjector().releaseRecordsPresenter();
			}});

		bottomDivider = findViewById(R.id.bottomDivider);
		progressBar = findViewById(R.id.progress);
		panelProgress = findViewById(R.id.wave_progress);
		btnPlay = findViewById(R.id.btn_play);
		btnStop = findViewById(R.id.btn_stop);
		btnNext = findViewById(R.id.btn_next);
		btnPrev = findViewById(R.id.btn_prev);
		btnDelete = findViewById(R.id.btn_delete);
		btnBookmarks = findViewById(R.id.btn_bookmarks);
		btnSort = findViewById(R.id.btn_sort);
		btnCheckBookmark = findViewById(R.id.btn_check_bookmark);
		txtEmpty = findViewById(R.id.txtEmpty);
		new ClickShrinkEffect(txtEmpty);
		txtTitle = findViewById(R.id.txt_title);
		new ClickShrinkEffect(btnBack);
		new ClickShrinkEffect(txtTitle);
		image_toolbar = findViewById(R.id.image_toolbar);
		btnPlay.setOnClickListener(this);
		new ClickShrinkEffect(btnPlay);
		btnStop.setOnClickListener(this);
		new ClickShrinkEffect(btnStop);
		btnNext.setOnClickListener(this);
		new ClickShrinkEffect(btnNext);
		btnPrev.setOnClickListener(this);
		new ClickShrinkEffect(btnPrev);
		btnDelete.setOnClickListener(this);
		new ClickShrinkEffect(btnDelete);
		btnBookmarks.setOnClickListener(this);
		new ClickShrinkEffect(btnBookmarks);
		btnCheckBookmark.setOnClickListener(this);
		new ClickShrinkEffect(btnCheckBookmark);
		btnSort.setOnClickListener(this);
		new ClickShrinkEffect(btnSort);
		playProgress = findViewById(R.id.play_progress);
		txtProgress = findViewById(R.id.txt_progress);
		txtDuration = findViewById(R.id.txt_duration);
		txtName = findViewById(R.id.txt_name);
		waveformView = findViewById(R.id.record);

		txtName.setOnClickListener(this);

		playProgress.setOnRubberSeekBarChangeListener(new RubberSeekBar.OnRubberSeekBarChangeListener() {
			@Override
			public void onProgressChanged(@NotNull RubberSeekBar rubberSeekBar, int progress, boolean fromUser) {
				if (fromUser) {
					int val = (int)AndroidUtils.dpToPx(progress * waveformView.getWaveformLength() / 1000);
					waveformView.seekPx(val);
					presenter.seekPlayback(val);
				}
			}
			@Override
			public void onStartTrackingTouch(@NotNull RubberSeekBar rubberSeekBar) {

			}

			@Override
			public void onStopTrackingTouch(@NotNull RubberSeekBar rubberSeekBar) {

			}
		});

		touchLayout = findViewById(R.id.touch_layout);
		touchLayout.setBackgroundResource(colorMap.getPlaybackPanelBackground());
		touchLayout.setOnThresholdListener(new TouchLayout.ThresholdListener() {
			@Override public void onTopThreshold() {
				hidePanel();
				presenter.stopPlayback();
			}
			@Override public void onBottomThreshold() {
				hidePanel();
				presenter.stopPlayback();
			}
			@Override public void onTouchDown() { }
			@Override public void onTouchUp() { }
		});

		recyclerView = findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
		layoutManager = new LinearLayoutManager(getApplicationContext());
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.addOnScrollListener(new MyScrollListener(layoutManager));

		recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrolled(@NonNull RecyclerView rv, int dx, int dy) {
				super.onScrolled(rv, dx, dy);
				handleToolbarScroll(dy);
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
					if (isListOnTop()) {
						AnimationUtil.viewElevationAnimation(toolbar, 0f, new Animator.AnimatorListener() {
							@Override public void onAnimationStart(Animator animation) { }
							@Override public void onAnimationEnd(Animator animation) {
								toolbar.setBackgroundResource(android.R.color.transparent);
							}
							@Override public void onAnimationCancel(Animator animation) { }
							@Override public void onAnimationRepeat(Animator animation) { }
						});
					}
				}
				if (adapter.getItemCount() < 5 || isListOnBottom()) {
					bottomDivider.setVisibility(View.GONE);
				} else {
					bottomDivider.setVisibility(View.VISIBLE);
				}
			}
		});

		SimpleWaveformView.setWaveformColorRes(colorMap.getPrimaryColorRes());
		adapter = new RecordsAdapter();
		adapter.setItemClickListener(new RecordsAdapter.ItemClickListener() {
			@Override
			public void onItemClick(View view, long id, String path, final int position) {
				presenter.setActiveRecord(id, new RecordsContract.Callback() {
					@Override public void onSuccess() {
						presenter.stopPlayback();
						if (startPlayback()) {
							adapter.setActiveItem(position);
						}
					}
					@Override public void onError(Exception e) {
						Timber.e(e);
					}
				});
			}
		});
		adapter.setOnAddToBookmarkListener(new RecordsAdapter.OnAddToBookmarkListener() {
			@Override public void onAddToBookmarks(int id) {
				presenter.addToBookmark(id);
			}
			@Override public void onRemoveFromBookmarks(int id) {
				presenter.removeFromBookmarks(id);
			}
		});
		adapter.setOnItemOptionListener(new RecordsAdapter.OnItemOptionListener() {
			@Override
			public void onItemOptionSelected(int menuId, final ListItem item) {
				switch (menuId) {
					case R.id.menu_share:
						AndroidUtils.shareAudioFile(getApplicationContext(), item.getPath(), item.getName());
						break;
					case R.id.menu_info:
						presenter.onRecordInfo(item.getName(), item.getDuration(), item.getPath(), item.getCreated());
						break;
					case R.id.menu_rename:
						setRecordName(item.getId(), new File(item.getPath()));
						break;
					case R.id.menu_open_with:
						AndroidUtils.openAudioFile(getApplicationContext(), item.getPath(), item.getName());
						break;
					case R.id.menu_delete:
						AndroidUtils.showSimpleDialog(
								RecordsActivity.this,
								R.drawable.ic_delete_forever,
								R.string.warning,
								getApplicationContext().getString(R.string.delete_record, item.getName()),
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {
										presenter.deleteRecord(item.getId(), item.getPath());
									}
								}
						);
						break;
				}
			}
		});
		adapter.setBtnTrashClickListener(new RecordsAdapter.BtnTrashClickListener() {
			@Override
			public void onClick() {
				startActivity(TrashActivity.getStartIntent(getApplicationContext()));
			}
		});
		recyclerView.setAdapter(adapter);

//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//			// Set the padding to match the Status Bar height
//			toolbar.setPadding(0, AndroidUtils.getStatusBarHeight(getApplicationContext()), 0, 0);
//		}
		presenter = ARApplication.getInjector().provideRecordsPresenter();

		waveformView.setOnSeekListener(new WaveformView.OnSeekListener() {
			@Override
			public void onStartSeek() {
				presenter.disablePlaybackProgressListener();
			}

			@Override
			public void onSeek(int px, long mills) {
				presenter.enablePlaybackProgressListener();
				presenter.seekPlayback(px);

				if (waveformView.getWaveformLength() > 0) {
					playProgress.setCurrentValue(1000 * (int) AndroidUtils.pxToDp(px) / waveformView.getWaveformLength());
				}
				txtProgress.setText(TimeUtils.formatTimeIntervalHourMinSec2(mills));
			}
			@Override
			public void onSeeking(int px, long mills) {
				if (waveformView.getWaveformLength() > 0) {
					playProgress.setCurrentValue(1000 * (int) AndroidUtils.pxToDp(px) / waveformView.getWaveformLength());
				}
				txtProgress.setText(TimeUtils.formatTimeIntervalHourMinSec2(mills));
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		presenter.onResumeView();
	}

	@Override
	public void showPlayerPanel() {
		if (touchLayout.getVisibility() != View.VISIBLE) {
			touchLayout.setVisibility(View.VISIBLE);
			if (touchLayout.getHeight() == 0) {
				touchLayout.setTranslationY(AndroidUtils.dpToPx(800));
			} else {
				touchLayout.setTranslationY(touchLayout.getHeight());
			}
			adapter.showFooter();
			final ViewPropertyAnimator animator = touchLayout.animate();
			animator.translationY(0)
					.setDuration(200)
					.setListener(new Animator.AnimatorListener() {
						@Override public void onAnimationStart(Animator animation) { }
						@Override public void onAnimationEnd(Animator animation) {
							int o = recyclerView.computeVerticalScrollOffset();
							int r = recyclerView.computeVerticalScrollRange();
							int e = recyclerView.computeVerticalScrollExtent();
							float k = (float)o/(float)(r-e);
							recyclerView.smoothScrollBy(0, (int)(touchLayout.getHeight()*k));
							animator.setListener(null);
						}
						@Override public void onAnimationCancel(Animator animation) { }
						@Override public void onAnimationRepeat(Animator animation) { }
					})
					.start();
		}
	}

	@Override
	public void startPlaybackService() {
		PlaybackService.startServiceForeground(getApplicationContext(), presenter.getRecordName());
	}

	public void hidePanel() {
		if (touchLayout.getVisibility() == View.VISIBLE) {
			adapter.hideFooter();
			showToolbar();
			final ViewPropertyAnimator animator = touchLayout.animate();
			animator.translationY(touchLayout.getHeight())
					.setDuration(200)
					.setListener(new Animator.AnimatorListener() {
						@Override public void onAnimationStart(Animator animation) { }
						@Override public void onAnimationEnd(Animator animation) {
							touchLayout.setVisibility(View.GONE);
							animator.setListener(null);
						}
						@Override public void onAnimationCancel(Animator animation) { }
						@Override public void onAnimationRepeat(Animator animation) { }
					})
					.start();
		}
	}

	private void showToolbar() {
		AnimationUtil.viewAnimationY(toolbar, 0f, null);
	}

	private boolean startPlayback() {
		if (FileUtil.isFileInExternalStorage(getApplicationContext(), presenter.getActiveRecordPath())) {
			if (checkStoragePermissionPlayback()) {
				presenter.startPlayback();
				return true;
			}
		} else {
			presenter.startPlayback();
			return true;
		}
		return false;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.btn_play:
				//This method Starts or Pause playback.
				startPlayback();
				break;
			case R.id.btn_stop:
				presenter.stopPlayback();
				hidePanel();
				break;
			case R.id.btn_next:
				presenter.pausePlayback();
				final long id = adapter.getNextTo(presenter.getActiveRecordId());
				presenter.setActiveRecord(id, new RecordsContract.Callback() {
					@Override public void onSuccess() {
						presenter.stopPlayback();
						if (startPlayback()) {
							int pos = adapter.findPositionById(id);
							if (pos >= 0) {
								recyclerView.scrollToPosition(pos);
								int o = recyclerView.computeVerticalScrollOffset();
								if (o > 0) {
									if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
										toolbar.setTranslationZ(getResources().getDimension(R.dimen.toolbar_elevation));
										toolbar.setBackgroundResource(colorMap.getPrimaryColorRes());
									}
								}
								adapter.setActiveItem(pos);
							}
						}
					}
					@Override public void onError(Exception e) {
						Timber.e(e);
					}
				});
				break;
			case R.id.btn_prev:
				presenter.pausePlayback();
				final long id2 = adapter.getPrevTo(presenter.getActiveRecordId());
				presenter.setActiveRecord(id2, new RecordsContract.Callback() {
					@Override public void onSuccess() {
						presenter.stopPlayback();
						if (startPlayback()) {
							int pos2 = adapter.findPositionById(id2);
							if (pos2 >= 0) {
								recyclerView.scrollToPosition(pos2);
								adapter.setActiveItem(pos2);
							}
						}
					}
					@Override public void onError(Exception e) {
						Timber.e(e);
					}
				});
				break;
			case R.id.btn_delete:
				presenter.pausePlayback();
				AndroidUtils.showSimpleDialog(
						RecordsActivity.this,
						R.drawable.ic_delete_forever,
						R.string.warning,
						getApplicationContext().getString(R.string.delete_record, presenter.getRecordName()),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								presenter.deleteActiveRecord();
							}
						}
				);
				break;
			case R.id.btn_check_bookmark:
				presenter.checkBookmarkActiveRecord();
				break;
			case R.id.btn_bookmarks:
				presenter.applyBookmarksFilter();
				break;
			case R.id.btn_sort:
				showMenu(view);
				break;
			case R.id.txt_name:
				if (presenter.getActiveRecordId() != -1) {
					setRecordName(presenter.getActiveRecordId(), new File(presenter.getActiveRecordPath()));
				}
				break;
		}
	}

	private void showMenu(View v) {
		PopupMenu popup = new PopupMenu(v.getContext(), v);
		popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				switch (item.getItemId()) {
					case R.id.menu_date:
						presenter.updateRecordsOrder(AppConstants.SORT_DATE);
						break;
					case R.id.menu_date_desc:
						presenter.updateRecordsOrder(AppConstants.SORT_DATE_DESC);
						break;
					case R.id.menu_name:
						presenter.updateRecordsOrder(AppConstants.SORT_NAME);
						break;
					case R.id.menu_name_desc:
						presenter.updateRecordsOrder(AppConstants.SORT_NAME_DESC);
						break;
					case R.id.menu_duration:
						presenter.updateRecordsOrder(AppConstants.SORT_DURATION);
						break;
					case R.id.menu_duration_desc:
						presenter.updateRecordsOrder(AppConstants.SORT_DURATION_DESC);
						break;
				}
				return false;
			}
		});
		MenuInflater inflater = popup.getMenuInflater();
		inflater.inflate(R.menu.menu_sort, popup.getMenu());
		AndroidUtils.insertMenuItemIcons(v.getContext(), popup);
		popup.show();
	}

	@Override
	protected void onStart() {
		super.onStart();
		presenter.bindView(this);
		presenter.loadRecords();
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (presenter != null) {
			presenter.unbindView();
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		ARApplication.getInjector().releaseRecordsPresenter();
	}

	private void handleToolbarScroll(int dy) {
		float inset = toolbar.getTranslationY() - dy;
		int height;
	   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			height = toolbar.getHeight() + AndroidUtils.getStatusBarHeight(getApplicationContext());
		} else {
			height = toolbar.getHeight();
		}

		if (inset < -height) {
			inset = -height;
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				toolbar.setTranslationZ(getResources().getDimension(R.dimen.toolbar_elevation));
				toolbar.setBackgroundResource(colorMap.getPrimaryColorRes());
			}
		}

		if (toolbar.getTranslationY() <= 0 && inset > 0) {
			toolbar.setTranslationY(0);
		} else {
			toolbar.setTranslationY(inset);
		}
	}

	public boolean isListOnTop() {
		return (layoutManager.findFirstCompletelyVisibleItemPosition() == 0);
	}

	public boolean isListOnBottom() {
		return (layoutManager.findLastCompletelyVisibleItemPosition() == adapter.getItemCount()-1);
	}

	@Override
	public void showPlayStart() {
		btnPlay.setImageResource(R.drawable.ic_pause_64);
	}

	@Override
	public void showPlayPause() {
		btnPlay.setImageResource(R.drawable.ic_play_64);
	}

	@Override
	public void showPlayStop() {
		waveformView.moveToStart();
		btnPlay.setImageResource(R.drawable.ic_play_64);
		playProgress.setCurrentValue(0);
		adapter.setActiveItem(-1);
	}

	@Override
	public void showNextRecord() {

	}

	@Override
	public void showPrevRecord() {

	}

	@Override
	public void showTrashBtn() {
		adapter.showTrash(true);
	}

	@Override
	public void hideTrashBtn() {
		adapter.showTrash(false);
	}

	@Override
	public void showWaveForm(int[] waveForm, long duration) {
		waveformView.setWaveform(waveForm);
		waveformView.setPxPerSecond(AndroidUtils.dpToPx(ARApplication.getDpPerSecond((float)duration/1000000f)));
	}

	@Override
	public void showDuration(final String duration) {
		txtProgress.setText(duration);
		txtDuration.setText(duration);
	}

	@Override
	public void onPlayProgress(final long mills, final int px, final int percent) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				waveformView.setPlayback(px);
				txtProgress.setText(TimeUtils.formatTimeIntervalHourMinSec2(mills));
				playProgress.setCurrentValue(percent);
			}
		});
	}

	@Override
	public void showRecords(List<ListItem> records, int order) {
		if (records.size() == 0) {
			txtEmpty.setVisibility(View.VISIBLE);
			adapter.setData(new ArrayList<ListItem>(), order);
		} else {
			adapter.setData(records, order);
			txtEmpty.setVisibility(View.GONE);
			if (touchLayout.getVisibility() == View.VISIBLE) {
				adapter.showFooter();
			}
		}
	}

	@Override
	public void addRecords(List<ListItem> records, int order) {
		adapter.addData(records, order);
		txtEmpty.setVisibility(View.GONE);
	}

	@Override
	public void showEmptyList() {
		txtEmpty.setText(R.string.no_records);
		txtEmpty.setVisibility(View.VISIBLE);
	}

	@Override
	public void showEmptyBookmarksList() {
		txtEmpty.setText(R.string.no_bookmarks);
		txtEmpty.setVisibility(View.VISIBLE);
	}

	@Override
	public void showPanelProgress() {
		panelProgress.setVisibility(View.VISIBLE);
	}

	@Override
	public void hidePanelProgress() {
		panelProgress.setVisibility(View.GONE);
	}

	@Override
	public void showRecordName(String name) {
		txtName.setText(name);
	}

	@Override
	public void onDeleteRecord(long id) {
//		adapter.deleteItem(id);
		presenter.loadRecords();
		if (adapter.getAudioRecordsCount() == 0) {
			showEmptyList();
		}
	}

	@Override
	public void hidePlayPanel() {
		hidePanel();
	}

	@Override
	public void addedToBookmarks(int id, boolean isActive) {
		if (isActive) {
			btnCheckBookmark.setImageResource(R.drawable.ic_bookmark);
		}
		adapter.markAddedToBookmarks(id);
	}

	@Override
	public void removedFromBookmarks(int id, boolean isActive) {
		if (isActive) {
			btnCheckBookmark.setImageResource(R.drawable.ic_bookmark_bordered);
		}
		adapter.markRemovedFromBookmarks(id);
	}

	@Override
	public void showSortType(int type) {
		switch (type) {
			case AppConstants.SORT_DATE:
				image_toolbar.setImageResource(R.drawable.calendar_toolbar);
				break;
			case AppConstants.SORT_DATE_DESC:
				image_toolbar.setImageResource(R.drawable.calendar_toolbar_oldst);
				break;
			case AppConstants.SORT_NAME:
				image_toolbar.setImageResource(R.drawable.sort_toolbar);
				break;
			case AppConstants.SORT_NAME_DESC:
				image_toolbar.setImageResource(R.drawable.sort_toolbar_z);
				break;
			case AppConstants.SORT_DURATION:
				image_toolbar.setImageResource(R.drawable.time_toolbar);
				break;
			case AppConstants.SORT_DURATION_DESC:
				image_toolbar.setImageResource(R.drawable.time_toolbar_longest);
				break;
		}
	}

	@Override
	public void showActiveRecord(int id) {
		int pos = adapter.findPositionById(id);
		if (pos >= 0) {
			adapter.setActiveItem(pos);
		}
	}

	@Override
	public void bookmarksSelected() {
		btnBookmarks.setImageResource(R.drawable.ic_bookmark);
		txtTitle.setText(R.string.bookmarks);
		btnSort.setVisibility(View.GONE);
		image_toolbar.setVisibility(View.GONE);
	}

	@Override
	public void bookmarksUnselected() {
		btnBookmarks.setImageResource(R.drawable.ic_bookmark_bordered);
		txtTitle.setText(R.string.records);
		btnSort.setVisibility(View.VISIBLE);
		image_toolbar.setVisibility(View.VISIBLE);
	}

	@Override
	public void showRecordInfo(RecordInfo info) {
		startActivity(ActivityInformation.getStartIntent(getApplicationContext(), info));
	}

	@Override
	public void showRecordsLostMessage(List<Record> list) {
		AndroidUtils.showLostRecordsDialog(this, list);
	}

	@Override
	public void showProgress() {
		progressBar.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideProgress() {
		progressBar.setVisibility(View.GONE);
	}

	@Override
	public void showError(String message) {
		new MaterialToast(getApplicationContext())
				.setMessage(message)
				.setIcon(R.mipmap.ic_kaba1)
				.setDuration(Toast.LENGTH_LONG)
				.show();
	}

	@Override
	public void showError(int resId) {
		new MaterialToast(getApplicationContext())
				.setMessage(resId)
				.setIcon(R.mipmap.ic_kaba1)
				.setDuration(Toast.LENGTH_LONG)
				.show();
	}

	@Override
	public void showMessage(int resId) {
		new MaterialToast(getApplicationContext())
				.setMessage(resId)
				.setIcon(R.mipmap.ic_kaba1)
				.setDuration(Toast.LENGTH_LONG)
				.show();
	}

	public void setRecordName(final long recordId, File file) {
		//Create dialog layout programmatically.
		LinearLayout container = new LinearLayout(getApplicationContext());
		container.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams containerLp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		container.setLayoutParams(containerLp);

		final EditText editText = new EditText(getApplicationContext());
		ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		editText.setLayoutParams(lp);
		editText.addTextChangedListener(new TextWatcher() {
			@Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
			@Override public void afterTextChanged(Editable s) {
				if (s.length() > AppConstants.MAX_RECORD_NAME_LENGTH) {
					s.delete(s.length() - 1, s.length());
				}
			}
			@Override public void onTextChanged(CharSequence s, int start, int before, int count) { }
		});
		editText.setTextColor(getResources().getColor(R.color.black));
		editText.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_medium));

		int pad = (int) getResources().getDimension(R.dimen.spacing_normal);
		ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(editText.getLayoutParams());
		params.setMargins(pad, pad, pad, pad);
		editText.setLayoutParams(params);
		container.addView(editText);

		final String fileName = FileUtil.removeFileExtension(file.getName());
		editText.setText(fileName);

		SweetAlertDialog dialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
				.setTitleText(getResources().getString(R.string.record_name))
				.setCancelText(getResources().getString(R.string.btn_cancel))
				.setConfirmText(getResources().getString(R.string.btn_save))
				.showCancelButton(true)
				.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
					@Override
					public void onClick(SweetAlertDialog sDialog) {
						hideKeyboard();
						// reuse previous dialog instance, keep widget user state, reset them if you need
						sDialog.setTitleText(getResources().getString(R.string.btn_cancel))
								.setContentText(getResources().getString(R.string.btn_cancell))
								.setConfirmText(getResources().getString(R.string.btn_cancelll))
								.showCancelButton(false)
								.setCancelClickListener(null)
								.setConfirmClickListener(null)
								.changeAlertType(SweetAlertDialog.ERROR_TYPE);
					}
				})
				.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
					@Override
					public void onClick(SweetAlertDialog sDialog) {
						String newName = editText.getText().toString();
						if (!fileName.equalsIgnoreCase(newName)) {
							presenter.renameRecord(recordId, newName);
							presenter.loadRecords();
						}
						hideKeyboard();
						sDialog.setTitleText(getResources().getString(R.string.btn_save))
								.setContentText(getResources().getString(R.string.btn_savee))
								.setConfirmText(getResources().getString(R.string.btn_cancelll))
								.showCancelButton(false)
								.setCancelClickListener(null)
								.setConfirmClickListener(null)
								.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
					}
				});
		dialog.setCustomView(container);
		dialog.show();
		editText.requestFocus();
		editText.setSelection(editText.getText().length());
		showKeyboard();
	}

	/** Show soft keyboard for a dialog. */
	public void showKeyboard(){
		InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
	}

	/** Hide soft keyboard after a dialog. */
	public void hideKeyboard(){
		InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
	}

	private boolean checkStoragePermissionPlayback() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
					&& checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
				requestPermissions(
						new String[]{
								Manifest.permission.WRITE_EXTERNAL_STORAGE,
								Manifest.permission.READ_EXTERNAL_STORAGE},
						REQ_CODE_READ_EXTERNAL_STORAGE_PLAYBACK);
				return false;
			}
		}
		return true;
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		if (requestCode == REQ_CODE_READ_EXTERNAL_STORAGE_PLAYBACK && grantResults.length > 0
				&& grantResults[0] == PackageManager.PERMISSION_GRANTED
				&& grantResults[1] == PackageManager.PERMISSION_GRANTED) {
			presenter.startPlayback();
		}
	}

	public class MyScrollListener extends EndlessRecyclerViewScrollListener {

		public <L extends RecyclerView.LayoutManager> MyScrollListener(L layoutManager) {
			super(layoutManager);
		}

		@Override
		public void onLoadMore(int page, int totalItemsCount) {
//			Timber.v("onLoadMore page = " + page + " count = " + totalItemsCount);
			presenter.loadRecordsPage(page);
		}
	}
}
