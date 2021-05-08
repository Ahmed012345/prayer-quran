package com.zaker.android.sapeh.app.main.activitymain.expandingcards;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sackcentury.shinebuttonlib.ShineButton;
import com.zaker.android.sapeh.R;
import com.zaker.android.sapeh.app.main.activitymain.LightTextView;
import com.zaker.android.sapeh.app.main.activitymain.expandingcards.pojo.Comment;
import com.zaker.android.sapeh.app.main.expandinglibrary.ECCardContentListItemAdapter;

import java.util.List;

@SuppressLint({"SetTextI18n", "InflateParams"})
public class CommentArrayAdapter extends ECCardContentListItemAdapter<Comment> {

    public CommentArrayAdapter(@NonNull Context context, @NonNull List<Comment> objects) {
        super(context, R.layout.list_element, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());

            rowView = inflater.inflate(R.layout.list_element, null);
            // configure view holder
            viewHolder = new ViewHolder();
            viewHolder.date =  rowView.findViewById(R.id.firstLineDate);
            viewHolder.line1 =  rowView.findViewById(R.id.firstLine);
            viewHolder.line2 =  rowView.findViewById(R.id.secondLine);
            viewHolder.simple_headbig =  rowView.findViewById(R.id.simple_headbig);

            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }

        final Comment objectItem = getItem(position);
        if (objectItem != null) {
            viewHolder.line1.setText(objectItem.getCommentPersonName());
            viewHolder.line2.setText(objectItem.getCommentText());
            viewHolder.date.setText(objectItem.getCommentDate());
            viewHolder.simple_headbig.setBackgroundResource(objectItem.getSimple_Headbig());

        }

        // Removing item by tap on comment icon
//        viewHolder.icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CommentArrayAdapter.this.remove(objectItem);
//                CommentArrayAdapter.this.notifyDataSetChanged();
//            }
//        });

        return rowView;
    }

    static class ViewHolder {
        LightTextView date;
        LightTextView line1;
        LightTextView line2;
        RelativeLayout simple_headbig;
    }

}
