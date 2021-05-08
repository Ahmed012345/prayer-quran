package com.zaker.android.sapeh.app.trash;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.marcoscg.materialtoast.MaterialToast;
import com.zaker.android.sapeh.ARApplication;
import com.zaker.android.sapeh.R;
import com.zaker.android.sapeh.app.info.ActivityInformation;
import com.zaker.android.sapeh.app.info.RecordInfo;
import com.zaker.android.sapeh.app.lostrecords.RecordItem;
import com.zaker.android.sapeh.util.AndroidUtils;
import com.zaker.android.sapeh.util.FileUtil;

/**
 * Created on 15.12.2019.
 * @author Dimowner
 */
public class TrashActivity extends LocalizationActivity implements TrashContract.View {

	private TrashContract.UserActionsListener presenter;

	private TrashAdapter adapter;
	private TextView txtEmpty;
	private TextView btnDeleteAll;

	public static Intent getStartIntent(Context context) {
		return new Intent(context, TrashActivity.class);
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		setTheme(ARApplication.getInjector().provideColorMap().getAppThemeResource());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trash);

		ImageButton btnBack = findViewById(R.id.btn_back);
		btnBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ARApplication.getInjector().releaseTrashPresenter();
				finish();
			}
		});

		btnDeleteAll = findViewById(R.id.btn_delete_all);
		btnDeleteAll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AndroidUtils.showSimpleDialog(
						TrashActivity.this,
						R.drawable.ic_delete_forever,
						R.string.warning,
						R.string.delete_all_records,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								presenter.deleteAllRecordsFromTrash();
							}
						}
				);
			}
		});

		txtEmpty = findViewById(R.id.txtEmpty);
		RecyclerView recyclerView = findViewById(R.id.recycler_view);
		recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
		adapter = new TrashAdapter();
		adapter.setOnItemClickListener(new TrashAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(RecordItem record) {
				presenter.onRecordInfo(FileUtil.removeFileExtension(record.getName()), record.getDuration()/1000, record.getPath(), record.getCreated());
			}

			@Override
			public void onDeleteItemClick(final RecordItem record) {
				AndroidUtils.showSimpleDialog(
						TrashActivity.this,
						R.drawable.ic_delete_forever,
						R.string.warning,
						getApplicationContext().getString(R.string.delete_record_forever, FileUtil.removeFileExtension(record.getName())),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								presenter.deleteRecordFromTrash(record.getId(), record.getPath());
							}
						}
				);
			}

			@SuppressLint("StringFormatInvalid")
			@Override
			public void onRestoreItemClick(final RecordItem record) {
				AndroidUtils.showSimpleDialog(
						TrashActivity.this,
						R.drawable.ic_restore_from_trash,
						R.string.warning,
						getApplicationContext().getString(R.string.restore_record, FileUtil.removeFileExtension(record.getName())),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								presenter.restoreRecordFromTrash(record.getId());
							}
						}
				);
			}
		});
		recyclerView.setAdapter(adapter);
		presenter = ARApplication.getInjector().provideTrashPresenter();
	}

	@Override
	protected void onStart() {
		super.onStart();
		presenter.bindView(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (presenter != null) {
			presenter.unbindView();
		}
	}

	@Override
	public void showProgress() {

	}

	@Override
	public void hideProgress() {

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
	public void showRecords(List<RecordItem> items) {
		adapter.setData(items);
	}

	@Override
	public void showRecordInfo(RecordInfo info) {
		startActivity(ActivityInformation.getStartIntent(getApplicationContext(), info));
	}

	@Override
	public void recordDeleted(int resId) {
		adapter.removeItem(resId);
		if (adapter.getItemCount() == 0) {
			showEmpty();
		}
	}

	@Override
	public void recordRestored(int resId) {
		adapter.removeItem(resId);
		if (adapter.getItemCount() == 0) {
			showEmpty();
		}
	}

	@Override
	public void allRecordsRemoved() {
		adapter.clearData();
		showEmpty();
	}

	@Override
	public void showEmpty() {
		txtEmpty.setVisibility(View.VISIBLE);
		btnDeleteAll.setVisibility(View.GONE);
	}

	@Override
	public void hideEmpty() {
		txtEmpty.setVisibility(View.GONE);
		btnDeleteAll.setVisibility(View.VISIBLE);
	}
}
