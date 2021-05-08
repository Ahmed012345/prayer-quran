package com.zaker.android.sapeh.app.main.activitymain;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.zaker.android.sapeh.R;

import java.util.List;
import java.util.Map;

class SampleAdapter extends ArrayAdapter<Map<String, Integer>> {

    static final String KEEY_NAME = "name";
    static final String KEY_NAME = "sname";
    static final String KEY_ICON = "icon";
    static final String KEY_COLOR = "color";


    private final LayoutInflater mInflater;
    private final List<Map<String, Integer>> mData;

    SampleAdapter(Context context, int layoutResourceId, List<Map<String,
            Integer>> data) {
        super(context, layoutResourceId, data);
        mData = data;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup
            parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_item, parent, false);
            viewHolder.textViewName =  convertView.findViewById(R.id.
                    text1);
            viewHolder.textViewName2=  convertView.findViewById(R.id.
                    text2);
            viewHolder.imageViewIcon =  convertView.findViewById(R.id.
                    image_view_icon);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textViewName.setText(mData.get(position).get(KEEY_NAME));
        viewHolder.textViewName2.setText(mData.get(position).get(KEY_NAME));
        viewHolder.imageViewIcon.setImageResource(mData.get(position).get
                (KEY_ICON));
        convertView.setBackgroundResource(mData.get(position).get(KEY_COLOR));

        return convertView;
    }

    private class ViewHolder {
        TextView textViewName;
        TextView textViewName2;
        ImageView imageViewIcon;
    }
}