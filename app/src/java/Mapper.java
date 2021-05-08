package com.zaker.android.sapeh;

import com.zaker.android.sapeh.app.records.ListItem;
import com.zaker.android.sapeh.data.database.Record;
import com.zaker.android.sapeh.util.FileUtil;
import com.zaker.android.sapeh.util.TimeUtils;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
	private Mapper() {}

	public static ListItem recordToListItem(Record record) {
		return new ListItem(
				record.getId(),
				ListItem.ITEM_TYPE_NORMAL,
				FileUtil.removeFileExtension(record.getName()),
				record.getName(),
				TimeUtils.formatTimeIntervalHourMinSec2(record.getDuration()/1000),
				record.getDuration()/1000,
				record.getCreated(),
				record.getAdded(),
				record.getPath(),
				record.isBookmarked(),
				record.getAmps());
	}

	public static List<ListItem> recordsToListItems(List<Record> records) {
		List<ListItem> items = new ArrayList<>(records.size());
		for (int i = 0; i < records.size(); i++) {
			items.add(recordToListItem(records.get(i)));
		}
		return items;
	}

}
