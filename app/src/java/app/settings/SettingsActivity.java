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

package com.zaker.android.sapeh.app.settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.marcoscg.materialtoast.MaterialToast;
import com.realpacific.clickshrinkeffect.ClickShrinkEffect;
import com.zaker.android.sapeh.ARApplication;
import com.zaker.android.sapeh.AppConstants;
import com.zaker.android.sapeh.ColorMap;
import com.zaker.android.sapeh.R;
import com.zaker.android.sapeh.app.trash.TrashActivity;
import com.zaker.android.sapeh.util.AndroidUtils;
import com.zaker.android.sapeh.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends LocalizationActivity implements SettingsContract.View, View.OnClickListener {

	private TextView txtTotalDuration;
	private TextView txtRecordsCount;
	private TextView txtAvailableSpace;

	private Switch swPublicDir;
	private Switch swRecordInStereo;
	private Switch swKeepScreenOn;
	private Switch swAskToRename;

	private Spinner nameFormatSelector;
	private Spinner formatSelector;
	private Spinner sampleRateSelector;
	private Spinner bitrateSelector;

	private SettingsContract.UserActionsListener presenter;
	private ColorMap colorMap;
	private ColorMap.OnThemeColorChangeListener onThemeColorChangeListener;
	private CompoundButton.OnCheckedChangeListener publicDirListener = new CompoundButton.OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(CompoundButton btn, boolean isChecked) {
			presenter.storeInPublicDir(isChecked);
			if (isChecked) {
				showDialogPublicDirInfo();
			} else {
				showDialogPrivateDirInfo();
			}
		}
	};


	public static Intent getStartIntent(Context context) {
		Intent intent = new Intent(context, SettingsActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		return intent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		colorMap = ARApplication.getInjector().provideColorMap();
		setTheme(colorMap.getAppThemeResource());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
				WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		LinearLayout toolbar = findViewById(R.id.toolbar);
		toolbar.setPadding(0, AndroidUtils.getStatusBarHeight(getApplicationContext()), 0, 0);

		View space = findViewById(R.id.space);
		ViewGroup.LayoutParams params = space.getLayoutParams();
		params.height = AndroidUtils.getNavigationBarHeight(getApplicationContext());
		space.setLayoutParams(params);

		TextView txtTrash = findViewById(R.id.btnTrash);
		ImageButton btnBack = findViewById(R.id.btn_back);
		new ClickShrinkEffect(btnBack);
//		TextView btnDeleteAll = findViewById(R.id.btnDeleteAll);
		btnBack.setOnClickListener(this);
//		btnDeleteAll.setOnClickListener(this);
		txtTrash.setOnClickListener(this);
		swPublicDir = findViewById(R.id.swPublicDir);
		swRecordInStereo = findViewById(R.id.swRecordInStereo);
		swKeepScreenOn = findViewById(R.id.swKeepScreenOn);
		swAskToRename = findViewById(R.id.swAskToRename);

		txtRecordsCount = findViewById(R.id.txt_records_count);
		txtTotalDuration= findViewById(R.id.txt_total_duration);
		txtAvailableSpace = findViewById(R.id.txt_available_space);

		swPublicDir.setOnCheckedChangeListener(publicDirListener);
		swRecordInStereo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton btn, boolean isChecked) {
				presenter.recordInStereo(isChecked);
			}
		});

		swKeepScreenOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton btn, boolean isChecked) {
				presenter.keepScreenOn(isChecked);
			}
		});
		swAskToRename.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton btn, boolean isChecked) {
				presenter.askToRenameAfterRecordingStop(isChecked);
			}
		});

		presenter = ARApplication.getInjector().provideSettingsPresenter();

		initThemeColorSelector();
		initNameFormatSelector();
		initFormatSelector();
		initSampleRateSelector();
		initBitrateSelector();
	}

	private void initThemeColorSelector() {
		final Spinner themeColor = findViewById(R.id.themeColor);
		List<AppSpinnerAdapter.ThemeItem> items = new ArrayList<>();
		String values[] = getResources().getStringArray(R.array.theme_colors);
		int[] colorRes = colorMap.getColorResources();
		for (int i = 0; i < values.length; i++) {
			items.add(new AppSpinnerAdapter.ThemeItem(values[i], getApplicationContext().getResources().getColor(colorRes[i])));
		}
		AppSpinnerAdapter adapter = new AppSpinnerAdapter(SettingsActivity.this,
				R.layout.list_item_spinner, R.id.txtItem, items, R.drawable.ic_color_lens);
		themeColor.setAdapter(adapter);

		onThemeColorChangeListener = new ColorMap.OnThemeColorChangeListener() {
			@Override
			public void onThemeColorChange(int pos) {
				setTheme(colorMap.getAppThemeResource());
				recreate();
			}
		};
		colorMap.addOnThemeColorChangeListener(onThemeColorChangeListener);

		if (colorMap.getSelected() > 0) {
			themeColor.setSelection(colorMap.getSelected());
		}
		themeColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				colorMap.updateColorMap(position);
			}
			@Override public void onNothingSelected(AdapterView<?> parent) { }
		});
	}

	private void initNameFormatSelector() {
		nameFormatSelector = findViewById(R.id.name_format);
		List<AppSpinnerAdapter.ThemeItem> items = new ArrayList<>();
		String[] values = new String[2];
		values[0] = getResources().getString(R.string.naming) + " " + FileUtil.generateRecordNameCounted(1);
		values[1] = getResources().getString(R.string.naming) + " " + FileUtil.generateRecordNameDate();
		for (int i = 0; i < values.length; i++) {
			items.add(new AppSpinnerAdapter.ThemeItem(values[i],
					getApplicationContext().getResources().getColor(colorMap.getPrimaryColorRes())));
		}
		AppSpinnerAdapter adapter = new AppSpinnerAdapter(SettingsActivity.this,
				R.layout.list_item_spinner, R.id.txtItem, items, R.drawable.ic_title);
		nameFormatSelector.setAdapter(adapter);

		nameFormatSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (position == 0) {
					presenter.setNamingFormat(AppConstants.NAMING_COUNTED);
				} else {
					presenter.setNamingFormat(AppConstants.NAMING_DATE);
				}
			}
			@Override public void onNothingSelected(AdapterView<?> parent) { }
		});
	}

	private void initFormatSelector() {
		formatSelector = findViewById(R.id.format);
		List<AppSpinnerAdapter.ThemeItem> items = new ArrayList<>();
		String[] values = getResources().getStringArray(R.array.formats);
		for (int i = 0; i < values.length; i++) {
			items.add(new AppSpinnerAdapter.ThemeItem(values[i],
					getApplicationContext().getResources().getColor(colorMap.getPrimaryColorRes())));
		}
		AppSpinnerAdapter adapter = new AppSpinnerAdapter(SettingsActivity.this,
				R.layout.list_item_spinner, R.id.txtItem, items, R.drawable.ic_audiotrack24);
		formatSelector.setAdapter(adapter);

		formatSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (position == 0) {
					presenter.setRecordingFormat(AppConstants.RECORDING_FORMAT_MP3);
				} else {
					presenter.setRecordingFormat(AppConstants.RECORDING_FORMAT_WAV);
				}
			}
			@Override public void onNothingSelected(AdapterView<?> parent) { }
		});
		if (ARApplication.isRecording()) {
			formatSelector.setEnabled(false);
			formatSelector.setClickable(false);
		}
	}

	private void initSampleRateSelector() {
		sampleRateSelector = findViewById(R.id.sample_rate);
		List<AppSpinnerAdapter.ThemeItem> items = new ArrayList<>();
		String[] values = getResources().getStringArray(R.array.sample_rates);
		for (int i = 0; i < values.length; i++) {
			items.add(new AppSpinnerAdapter.ThemeItem(values[i],
					getApplicationContext().getResources().getColor(colorMap.getPrimaryColorRes())));
		}
		AppSpinnerAdapter adapter = new AppSpinnerAdapter(SettingsActivity.this,
				R.layout.list_item_spinner, R.id.txtItem, items, R.drawable.ic_audiotrack24);
		sampleRateSelector.setAdapter(adapter);

		sampleRateSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				presenter.setSampleRate(position);
			}
			@Override public void onNothingSelected(AdapterView<?> parent) { }
		});
	}

	private void initBitrateSelector() {
		bitrateSelector = findViewById(R.id.bit_rate);
		List<AppSpinnerAdapter.ThemeItem> items3 = new ArrayList<>();
		String[] values3 = getResources().getStringArray(R.array.bit_rates);
		for (int i = 0; i < values3.length; i++) {
			items3.add(new AppSpinnerAdapter.ThemeItem(values3[i],
					getApplicationContext().getResources().getColor(colorMap.getPrimaryColorRes())));
		}
		AppSpinnerAdapter adapter3 = new AppSpinnerAdapter(SettingsActivity.this,
				R.layout.list_item_spinner, R.id.txtItem, items3, R.drawable.ic_audiotrack24);
		bitrateSelector.setAdapter(adapter3);

		bitrateSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				presenter.setRecordingBitrate(position);
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	}

	@Override
	protected void onStart() {
		super.onStart();
		presenter.bindView(this);
		presenter.loadSettings();
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
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_back:
				ARApplication.getInjector().releaseSettingsPresenter();
				finish();
				break;
			case R.id.btnTrash:
				startActivity(TrashActivity.getStartIntent(getApplicationContext()));
				break;
//			case R.id.btnDeleteAll:
//				AlertDialog.Builder builder = new AlertDialog.Builder(this);
//				builder.setTitle(R.string.warning)
//						.setIcon(R.drawable.ic_delete_forever)
//						.setMessage(R.string.delete_all_records)
//						.setCancelable(false)
//						.setPositiveButton(R.string.btn_yes, new DialogInterface.OnClickListener() {
//							public void onClick(DialogInterface dialog, int id) {
//								presenter.deleteAllRecords();
//								dialog.dismiss();
//							}
//						})
//						.setNegativeButton(R.string.btn_no,
//								new DialogInterface.OnClickListener() {
//									public void onClick(DialogInterface dialog, int id) {
//										dialog.dismiss();
//									}
//								});
//				AlertDialog alert = builder.create();
//				alert.show();
//				break;
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		ARApplication.getInjector().releaseSettingsPresenter();
	}

	@Override
	public void showStoreInPublicDir(boolean b) {
		swPublicDir.setOnCheckedChangeListener(null);
		swPublicDir.setChecked(b);
		swPublicDir.setOnCheckedChangeListener(publicDirListener);
	}

	@Override
	public void showKeepScreenOn(boolean b) {
		swKeepScreenOn.setChecked(b);
	}

	@Override
	public void showRecordInStereo(boolean b) {
		swRecordInStereo.setChecked(b);
	}

	@Override
	public void showAskToRenameAfterRecordingStop(boolean b) {
		swAskToRename.setChecked(b);
	}

	@Override
	public void showRecordingBitrate(int bitrate) {
		bitrateSelector.setSelection(bitrate);
	}

	@Override
	public void showRecordingSampleRate(int rate) {
		sampleRateSelector.setSelection(rate);
	}

	@Override
	public void showRecordingFormat(int format) {
		formatSelector.setSelection(format);
	}

	@Override
	public void showNamingFormat(int format) {
		nameFormatSelector.setSelection(format);
	}

	@Override
	public void showAllRecordsDeleted() {
		new MaterialToast(getApplicationContext())
				.setMessage(R.string.all_records_deleted)
				.setIcon(R.mipmap.ic_kaba1)
				.setDuration(Toast.LENGTH_LONG)
				.show();
	}

	@Override
	public void showFailDeleteAllRecords() {
		new MaterialToast(getApplicationContext())
				.setMessage(R.string.failed_to_delete_all_records)
				.setIcon(R.mipmap.ic_kaba1)
				.setDuration(Toast.LENGTH_LONG)
				.show();
	}

	@Override
	public void showTotalRecordsDuration(String duration) {
		txtTotalDuration.setText(getResources().getString(R.string.total_duration, duration));
	}

	@Override
	public void showRecordsCount(int count) {
		txtRecordsCount.setText(getResources().getString(R.string.total_record_count, count));
	}

	@Override
	public void showAvailableSpace(String space) {
		txtAvailableSpace.setText(getResources().getString(R.string.available_space, space));
	}

	@Override
	public void showBitrateSelector() {
		bitrateSelector.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideBitrateSelector() {
		bitrateSelector.setVisibility(View.GONE);
	}

	@Override
	public void showDialogPublicDirInfo() {
		AndroidUtils.showDialog(this, R.string.warning, R.string.public_dir_warning,
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
					}
				}, null
		);
	}

	@Override
	public void showDialogPrivateDirInfo() {
		AndroidUtils.showDialog(this, R.string.warning, R.string.private_dir_warning,
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
					}
				}, null
		);
	}

	@Override
	public void showProgress() {
//		TODO: showProgress
	}

	@Override
	public void hideProgress() {
//		TODO: hideProgress
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
}
