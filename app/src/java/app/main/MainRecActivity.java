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

package com.zaker.android.sapeh.app.main;

import android.Manifest;
import android.animation.Animator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.zaker.android.sapeh.IntArrayList;
import com.zaker.android.sapeh.R;
import com.zaker.android.sapeh.app.DownloadService;
import com.zaker.android.sapeh.app.PlaybackService;
import com.zaker.android.sapeh.app.RecordingService;
import com.zaker.android.sapeh.app.info.ActivityInformation;
import com.zaker.android.sapeh.app.info.RecordInfo;
import com.zaker.android.sapeh.app.main.activitymain.MainActivity;
import com.zaker.android.sapeh.app.main.sweetalert.SweetAlertDialog;
import com.zaker.android.sapeh.app.records.RecordsActivity;
import com.zaker.android.sapeh.app.settings.SettingsActivity;
import com.zaker.android.sapeh.app.widget.WaveformView;
import com.zaker.android.sapeh.data.database.Record;
import com.zaker.android.sapeh.util.AndroidUtils;
import com.zaker.android.sapeh.util.AnimationUtil;
import com.zaker.android.sapeh.util.FileUtil;
import com.zaker.android.sapeh.util.TimeUtils;

import java.io.File;
import java.util.List;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

public class MainRecActivity extends LocalizationActivity implements MainContract.View, View.OnClickListener {

// TODO: Fix WaveForm blinking when seek
// TODO: Fix waveform when long record (there is no waveform)
// TODO: Welcome screen theme color, rec format and quality, location dir, name format (date or record)
// TODO: Ability to search by record name in list
// TODO: Display recording info on main activity.
// TODO: Ability to scroll up from the bottom of the list
// TODO: Guidelines
// TODO: Stop infinite loop when pause WAV recording
// TODO: Report some sensitive error to Crashlytics manually.

	public static final int REQ_CODE_REC_AUDIO_AND_WRITE_EXTERNAL = 101;
	public static final int REQ_CODE_RECORD_AUDIO = 303;
	public static final int REQ_CODE_WRITE_EXTERNAL_STORAGE = 404;
	public static final int REQ_CODE_READ_EXTERNAL_STORAGE_IMPORT = 405;
	public static final int REQ_CODE_READ_EXTERNAL_STORAGE_PLAYBACK = 406;
	public static final int REQ_CODE_IMPORT_AUDIO = 11;

	private WaveformView waveformView;
	private TextView txtProgress;
	private TextView txtDuration;
	private TextView txtZeroTime;
	private TextView txtName;
	private ImageButton btnPlay;
	private ImageButton btnStop;
	private ImageButton btnRecord;
	private ImageButton btnDelete;
	private ImageButton btnRecordingStop;
	private ImageButton btnShare;
	private ImageButton btnRecordsList;
	private ImageButton btnSettings;
	private ImageButton btnImport;
	private ProgressBar progressBar;
	private RubberSeekBar playProgress;
	private LinearLayout pnlImportProgress;
	private LinearLayout pnlRecordProcessing;

	private MainContract.UserActionsListener presenter;
	private ColorMap colorMap;
	private ColorMap.OnThemeColorChangeListener onThemeColorChangeListener;
	Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		colorMap = ARApplication.getInjector().provideColorMap();
		setTheme(colorMap.getAppThemeResource());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activityrecmain);

		waveformView = findViewById(R.id.record);
		txtProgress = findViewById(R.id.txt_progress);
		txtDuration = findViewById(R.id.txt_duration);
		txtZeroTime = findViewById(R.id.txt_zero_time);
		txtName = findViewById(R.id.txt_name);
		btnPlay = findViewById(R.id.btn_play);
		btnRecord = findViewById(R.id.btn_record);
		btnRecordingStop = findViewById(R.id.btn_record_stop);
		btnDelete = findViewById(R.id.btn_record_delete);
		btnStop = findViewById(R.id.btn_stop);
		btnRecordsList = findViewById(R.id.btn_records_list);
		btnSettings = findViewById(R.id.btn_settings);
		btnShare = findViewById(R.id.btn_share);
		btnImport = findViewById(R.id.btn_import);
		progressBar = findViewById(R.id.progress);
		playProgress = findViewById(R.id.play_progress);
		pnlImportProgress = findViewById(R.id.pnl_import_progress);
		pnlRecordProcessing = findViewById(R.id.pnl_record_processing);

		txtProgress.setText(TimeUtils.formatTimeIntervalHourMinSec2(0));

		btnDelete.setVisibility(View.INVISIBLE);
		btnDelete.setEnabled(false);
		btnRecordingStop.setVisibility(View.INVISIBLE);
		btnRecordingStop.setEnabled(false);

		btnPlay.setOnClickListener(this);
		btnRecord.setOnClickListener(this);
		new ClickShrinkEffect(btnRecord);
		btnRecordingStop.setOnClickListener(this);
		btnDelete.setOnClickListener(this);
		btnStop.setOnClickListener(this);
		btnRecordsList.setOnClickListener(this);
		new ClickShrinkEffect(btnRecordsList);
		btnSettings.setOnClickListener(this);
		new ClickShrinkEffect(btnSettings);
		btnShare.setOnClickListener(this);
		btnImport.setOnClickListener(this);
		new ClickShrinkEffect(btnImport);
		txtName.setOnClickListener(this);
		new ClickShrinkEffect(txtName);
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

		presenter = ARApplication.getInjector().provideMainPresenter();
		presenter.executeFirstRun();

		waveformView.setOnSeekListener(new WaveformView.OnSeekListener() {
			@Override
			public void onStartSeek() {
				presenter.disablePlaybackProgressListener();
			}

			@Override
			public void onSeek(int px, long mills) {
				presenter.enablePlaybackProgressListener();
				presenter.seekPlayback(px);

				int length = waveformView.getWaveformLength();
				if (length > 0) {
					playProgress.setCurrentValue(1000 * (int) AndroidUtils.pxToDp(px) / length);
				}
				txtProgress.setText(TimeUtils.formatTimeIntervalHourMinSec2(mills));
			}
			@Override
			public void onSeeking(int px, long mills) {
				int length = waveformView.getWaveformLength();
				if (length > 0) {
					playProgress.setCurrentValue(1000 * (int) AndroidUtils.pxToDp(px) / length);
				}
				txtProgress.setText(TimeUtils.formatTimeIntervalHourMinSec2(mills));
			}
		});
		onThemeColorChangeListener = new ColorMap.OnThemeColorChangeListener() {
			@Override
			public void onThemeColorChange(int pos) {
				setTheme(colorMap.getAppThemeResource());
				recreate();
			}
		};
		colorMap.addOnThemeColorChangeListener(onThemeColorChangeListener);
	}

	@Override
	protected void onStart() {
		super.onStart();
		presenter.bindView(this);
		presenter.setAudioRecorder(ARApplication.getInjector().provideAudioRecorder());
		presenter.updateRecordingDir(getApplicationContext());
		presenter.loadActiveRecord();
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (presenter != null) {
			presenter.unbindView();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		colorMap.removeOnThemeColorChangeListener(onThemeColorChangeListener);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.btn_play:
				//This method Starts or Pause playback.
				if (FileUtil.isFileInExternalStorage(getApplicationContext(), presenter.getActiveRecordPath())) {
					if (checkStoragePermissionPlayback()) {
						presenter.startPlayback();
					}
				} else {
					presenter.startPlayback();
				}
				break;
			case R.id.btn_record:
				if (checkRecordPermission2()) {
					if (checkStoragePermission2()) {
						//Start or stop recording
						presenter.startRecording(getApplicationContext());
					}
				}
				break;
			case R.id.btn_record_stop:
				presenter.stopRecording(false);
				break;
			case R.id.btn_record_delete:
				presenter.cancelRecording();
				break;
			case R.id.btn_stop:
				presenter.stopPlayback();
				break;
			case R.id.btn_records_list:
				startActivity(RecordsActivity.getStartIntent(getApplicationContext()));
				break;
			case R.id.btn_settings:
				startActivity(SettingsActivity.getStartIntent(getApplicationContext()));
				break;
			case R.id.btn_share:
//				AndroidUtils.shareAudioFile(getApplicationContext(), presenter.getActiveRecordPath(), presenter.getActiveRecordName());
				showMenu(view);
				break;
			case R.id.btn_import:
				if (checkStoragePermissionImport()) {
					startFileSelector();
				}
				break;
			case R.id.txt_name:
				if (presenter.getActiveRecordId() != -1) {
					setRecordName(presenter.getActiveRecordId(), new File(presenter.getActiveRecordPath()), false);
				}
				break;
		}
	}

	private void startFileSelector() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			Intent intent_upload = new Intent();
			intent_upload.setType("audio/*");
			intent_upload.addCategory(Intent.CATEGORY_OPENABLE);
//		intent_upload.setAction(Intent.ACTION_GET_CONTENT);
			intent_upload.setAction(Intent.ACTION_OPEN_DOCUMENT);
			startActivityForResult(intent_upload, REQ_CODE_IMPORT_AUDIO);
		} else {
			startActivity(new Intent(context, MainActivity.class));
			Bungee.shrink(context);
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQ_CODE_IMPORT_AUDIO && resultCode == RESULT_OK){
			presenter.importAudioFile(getApplicationContext(), data.getData());
		}
	}

	@Override
	public void keepScreenOn(boolean on) {
		if (on) {
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		} else {
			getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		}
	}

	@Override
	public void showProgress() {
		waveformView.setVisibility(View.INVISIBLE);
		progressBar.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideProgress() {
		waveformView.setVisibility(View.VISIBLE);
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

	@Override
	public void showRecordingStart() {
		txtName.setClickable(false);
		txtName.setFocusable(false);
		txtName.setCompoundDrawables(null, null, null, null);
		txtName.setVisibility(View.VISIBLE);
		txtName.setText(R.string.recording_progress);
		txtZeroTime.setVisibility(View.INVISIBLE);
		txtDuration.setVisibility(View.INVISIBLE);
		btnRecord.setImageResource(R.drawable.ic_pause_circle_filled);
		btnPlay.setEnabled(false);
		btnImport.setEnabled(false);
		btnShare.setEnabled(false);
		btnDelete.setVisibility(View.VISIBLE);
		btnDelete.setEnabled(true);
		btnRecordingStop.setVisibility(View.VISIBLE);
		btnRecordingStop.setEnabled(true);
		playProgress.setCurrentValue(0);
		playProgress.setEnabled(false);
		txtDuration.setText(R.string.zero_time);
		waveformView.showRecording();
	}

	@Override
	public void showRecordingStop() {
		txtName.setClickable(true);
		txtName.setFocusable(true);
		txtName.setText("");
		txtZeroTime.setVisibility(View.VISIBLE);
		txtDuration.setVisibility(View.VISIBLE);
		txtName.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_pencil_small), null);
		txtName.setVisibility(View.INVISIBLE);
		if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
			btnRecord.setImageResource(R.drawable.ic_rec_red);
	    }
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			btnRecord.setImageResource(R.drawable.ic_record_rec);
		}
		btnPlay.setEnabled(true);
		btnImport.setEnabled(true);
		btnShare.setEnabled(true);
		playProgress.setEnabled(true);
		btnDelete.setVisibility(View.INVISIBLE);
		btnDelete.setEnabled(false);
		btnRecordingStop.setVisibility(View.INVISIBLE);
		btnRecordingStop.setEnabled(false);
		waveformView.hideRecording();
		waveformView.clearRecordingData();
	}

	@Override
	public void showRecordingPause() {
		txtName.setClickable(false);
		txtName.setFocusable(false);
		txtName.setCompoundDrawables(null, null, null, null);
		txtName.setText(R.string.recording_paused);
		txtName.setVisibility(View.VISIBLE);
		btnImport.setEnabled(false);
		if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
			btnRecord.setImageResource(R.drawable.ic_rec_red);
		}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			btnRecord.setImageResource(R.drawable.ic_record_rec);
		}
		btnDelete.setVisibility(View.VISIBLE);
		btnDelete.setEnabled(true);
		btnRecordingStop.setVisibility(View.VISIBLE);
		btnRecordingStop.setEnabled(true);
		playProgress.setEnabled(false);
		btnShare.setEnabled(false);
	}

	@Override
	public void askRecordingNewName(long id, File file) {
		setRecordName(id, file, true);
	}

	@Override
	public void onRecordingProgress(long mills, int amp) {
		txtProgress.setText(TimeUtils.formatTimeIntervalHourMinSec2(mills));
		waveformView.addRecordAmp(amp);
	}

	@Override
	public void startRecordingService() {
		Intent intent = new Intent(getApplicationContext(), RecordingService.class);
		intent.setAction(RecordingService.ACTION_START_RECORDING_SERVICE);
		startService(intent);
	}

	@Override
	public void stopRecordingService() {
		Intent intent = new Intent(getApplicationContext(), RecordingService.class);
		intent.setAction(RecordingService.ACTION_STOP_RECORDING_SERVICE);
		startService(intent);
	}

	@Override
	public void startPlaybackService(final String name) {
		PlaybackService.startServiceForeground(getApplicationContext(), name);
	}

	@Override
	public void showPlayStart(boolean animate) {
		btnRecord.setEnabled(false);
		if (animate) {
			AnimationUtil.viewAnimationX(btnPlay, -75f, new Animator.AnimatorListener() {
				@Override public void onAnimationStart(Animator animation) { }
				@Override public void onAnimationEnd(Animator animation) {
					btnStop.setVisibility(View.VISIBLE);
					btnPlay.setImageResource(R.drawable.ic_pause);
				}
				@Override public void onAnimationCancel(Animator animation) { }
				@Override public void onAnimationRepeat(Animator animation) { }
			});
		} else {
			btnPlay.setTranslationX(-75f);
			btnStop.setVisibility(View.VISIBLE);
			btnPlay.setImageResource(R.drawable.ic_pause);
		}
	}

	@Override
	public void showPlayPause() {
		btnPlay.setImageResource(R.drawable.ic_play);
	}

	@Override
	public void showPlayStop() {
		btnPlay.setImageResource(R.drawable.ic_play);
		waveformView.moveToStart();
		btnRecord.setEnabled(true);
		playProgress.setCurrentValue(0);
		txtProgress.setText(TimeUtils.formatTimeIntervalHourMinSec2(0));
		AnimationUtil.viewAnimationX(btnPlay, 0f, new Animator.AnimatorListener() {
			@Override public void onAnimationStart(Animator animation) { }
			@Override public void onAnimationEnd(Animator animation) {
				btnStop.setVisibility(View.GONE);
			}
			@Override public void onAnimationCancel(Animator animation) { }
			@Override public void onAnimationRepeat(Animator animation) { }
		});
	}

	@Override
	public void showWaveForm(int[] waveForm, long duration) {
		if (waveForm.length > 0) {
			btnPlay.setVisibility(View.VISIBLE);
			txtDuration.setVisibility(View.VISIBLE);
			txtZeroTime.setVisibility(View.VISIBLE);
		} else {
			btnPlay.setVisibility(View.INVISIBLE);
			txtDuration.setVisibility(View.INVISIBLE);
			txtZeroTime.setVisibility(View.INVISIBLE);
		}
		waveformView.setWaveform(waveForm);
		waveformView.setPxPerSecond(AndroidUtils.dpToPx(ARApplication.getDpPerSecond((float)duration/1000000f)));
	}

	@Override
	public void waveFormToStart() {
		waveformView.seekPx(0);
	}

	@Override
	public void showDuration(final String duration) {
		txtDuration.setText(duration);
	}

	@Override
	public void showRecordingProgress(String progress) {
		txtProgress.setText(progress);
	}

	@Override
	public void showName(String name) {
		if (name == null || name.isEmpty()) {
			txtName.setVisibility(View.INVISIBLE);
		} else if (txtName.getVisibility() == View.INVISIBLE) {
			txtName.setVisibility(View.VISIBLE);
		}
		txtName.setText(name);
	}

	@Override
	public void askDeleteRecord(String name) {
		new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
				.setTitleText(getResources().getString(R.string.warning))
				.setContentText(getApplicationContext().getString(R.string.delete_record, name))
				.setConfirmText(getResources().getString(R.string.btn_yes))
				.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
					@Override
					public void onClick(SweetAlertDialog sDialog) {
						presenter.deleteActiveRecord(false);
						// reuse previous dialog instance
						sDialog.setTitleText(getResources().getString(R.string.btn_cc))
								.setContentText(getResources().getString(R.string.btn_ccc))
								.setConfirmText(getResources().getString(R.string.btn_cancelll))
								.setConfirmClickListener(null)
								.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
					}
				})
				.show();
	}

	@Override
	public void askDeleteRecordForever() {
		new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
				.setTitleText(getResources().getString(R.string.warning))
				.setContentText(getApplicationContext().getString(R.string.delete_this_record))
				.setConfirmText(getResources().getString(R.string.btn_yes))
				.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
					@Override
					public void onClick(SweetAlertDialog sDialog) {
						presenter.stopRecording(true);
						// reuse previous dialog instance
						sDialog.setTitleText(getResources().getString(R.string.btn_cc))
								.setContentText(getResources().getString(R.string.btn_ccc))
								.setConfirmText(getResources().getString(R.string.btn_cancelll))
								.setConfirmClickListener(null)
								.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
					}
				})
				.show();
	}

	@Override
	public void showRecordInfo(RecordInfo info) {
		startActivity(ActivityInformation.getStartIntent(getApplicationContext(), info));
	}

	@Override
	public void updateRecordingView(IntArrayList data) {
		waveformView.showRecording();
		waveformView.setRecordingData(data);
	}

	@Override
	public void showRecordsLostMessage(List<Record> list) {
		AndroidUtils.showLostRecordsDialog(this, list);
	}

	@Override
	public void onPlayProgress(final long mills, final int px, int percent) {
		playProgress.setCurrentValue(percent);
		waveformView.setPlayback(px);
		txtProgress.setText(TimeUtils.formatTimeIntervalHourMinSec2(mills));
	}

	@Override
	public void showImportStart() {
		btnImport.setVisibility(View.INVISIBLE);
		pnlImportProgress.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideImportProgress() {
		pnlImportProgress.setVisibility(View.INVISIBLE);
		btnImport.setVisibility(View.VISIBLE);
	}

	@Override
	public void showOptionsMenu() {
		btnShare.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideOptionsMenu() {
		btnShare.setVisibility(View.INVISIBLE);
	}

	@Override
	public void showRecordProcessing() {
		pnlRecordProcessing.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideRecordProcessing() {
		pnlRecordProcessing.setVisibility(View.INVISIBLE);
	}

	private void showMenu(View v) {
		PopupMenu popup = new PopupMenu(v.getContext(), v);
		popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				switch (item.getItemId()) {
					case R.id.menu_share:
						AndroidUtils.shareAudioFile(getApplicationContext(), presenter.getActiveRecordPath(), presenter.getActiveRecordName());
						break;
					case R.id.menu_info:
						presenter.onRecordInfo();
						break;
					case R.id.menu_rename:
						String path = presenter.getActiveRecordPath();
						if (path != null) {
							setRecordName(presenter.getActiveRecordId(), new File(path), false);
						}
						break;
					case R.id.menu_open_with:
						AndroidUtils.openAudioFile(getApplicationContext(), presenter.getActiveRecordPath(), presenter.getActiveRecordName());
						break;
					case R.id.menu_delete:
						askDeleteRecord(presenter.getActiveRecordName());
						break;
				}
				return false;
			}
		});
		MenuInflater inflater = popup.getMenuInflater();
		inflater.inflate(R.menu.menu_more, popup.getMenu());
		AndroidUtils.insertMenuItemIcons(v.getContext(), popup);
		popup.show();
	}

	public void setRecordName(final long recordId, File file, boolean showCheckbox) {
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
		if (showCheckbox) {
			container.addView(createCheckerView());
		}

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

	public CheckBox createCheckerView() {
		final CheckBox checkBox = new CheckBox(getApplicationContext());
		int color = getResources().getColor(R.color.black);
		checkBox.setTextColor(color);
		ColorStateList colorStateList = new ColorStateList(
				new int[][]{
						new int[]{-android.R.attr.state_checked}, // unchecked
						new int[]{android.R.attr.state_checked}  // checked
				},
				new int[]{
						color,
						color
				}
		);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			checkBox.setButtonTintList(colorStateList);
		}
		checkBox.setText(R.string.dont_ask_again_rename);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		int PADD = (int) getResources().getDimension(R.dimen.spacing_normal);
		params.setMargins(PADD, 0, PADD, PADD);
		checkBox.setLayoutParams(params);
		checkBox.setSaveEnabled(false);
		checkBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
		checkBox.setPadding(
				checkBox.getPaddingLeft()+(int) getResources().getDimension(R.dimen.spacing_small),
				checkBox.getPaddingTop(),
				checkBox.getPaddingRight(),
				checkBox.getPaddingBottom());

		checkBox.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				presenter.dontAskRename();
			}
		});
		return checkBox;
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

	private boolean checkStoragePermissionImport() {
		if (presenter.isStorePublic()) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
				if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
						&& checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
					requestPermissions(
							new String[]{
									Manifest.permission.WRITE_EXTERNAL_STORAGE,
									Manifest.permission.READ_EXTERNAL_STORAGE},
							REQ_CODE_READ_EXTERNAL_STORAGE_IMPORT);
					return false;
				}
			}
		}
		return true;
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

	private boolean checkRecordPermission2() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
				requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, REQ_CODE_RECORD_AUDIO);
				return false;
			}
		}
		return true;
	}

	private boolean checkStoragePermission2() {
		if (presenter.isStorePublic()) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
				if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
					AndroidUtils.showDialog(this, R.string.warning, R.string.need_write_permission,
							new View.OnClickListener() {
								@Override
								public void onClick(View v) {
									requestPermissions(
											new String[]{
													Manifest.permission.WRITE_EXTERNAL_STORAGE,
													Manifest.permission.READ_EXTERNAL_STORAGE},
											REQ_CODE_WRITE_EXTERNAL_STORAGE);
								}
							}, null
//							new View.OnClickListener() {
//								@Override
//								public void onClick(View v) {
//									presenter.setStoragePrivate(getApplicationContext());
//									presenter.startRecording();
//								}
//							}
					);
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		if (requestCode == REQ_CODE_REC_AUDIO_AND_WRITE_EXTERNAL && grantResults.length > 0
					&& grantResults[0] == PackageManager.PERMISSION_GRANTED
					&& grantResults[1] == PackageManager.PERMISSION_GRANTED
					&& grantResults[2] == PackageManager.PERMISSION_GRANTED) {
			presenter.startRecording(getApplicationContext());
		} else if (requestCode == REQ_CODE_RECORD_AUDIO && grantResults.length > 0
				&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {
			if (checkStoragePermission2()) {
				presenter.startRecording(getApplicationContext());
			}
		} else if (requestCode == REQ_CODE_WRITE_EXTERNAL_STORAGE && grantResults.length > 0
				&& grantResults[0] == PackageManager.PERMISSION_GRANTED
				&& grantResults[1] == PackageManager.PERMISSION_GRANTED) {
			if (checkRecordPermission2()) {
				presenter.startRecording(getApplicationContext());
			}
		} else if (requestCode == REQ_CODE_READ_EXTERNAL_STORAGE_IMPORT && grantResults.length > 0
				&& grantResults[0] == PackageManager.PERMISSION_GRANTED
				&& grantResults[1] == PackageManager.PERMISSION_GRANTED) {
			startFileSelector();
		} else if (requestCode == REQ_CODE_READ_EXTERNAL_STORAGE_PLAYBACK && grantResults.length > 0
				&& grantResults[0] == PackageManager.PERMISSION_GRANTED
				&& grantResults[1] == PackageManager.PERMISSION_GRANTED) {
			presenter.startPlayback();
		} else if (requestCode == REQ_CODE_WRITE_EXTERNAL_STORAGE && grantResults.length > 0
				&& (grantResults[0] == PackageManager.PERMISSION_DENIED
				|| grantResults[1] == PackageManager.PERMISSION_DENIED)) {
			presenter.setStoragePrivate(getApplicationContext());
			presenter.startRecording(getApplicationContext());
		}
	}
}
