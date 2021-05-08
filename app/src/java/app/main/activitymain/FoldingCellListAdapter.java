package com.zaker.android.sapeh.app.main.activitymain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.ramotion.foldingcell.FoldingCell;
import com.zaker.android.sapeh.R;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Simple example of ListAdapter for using with Folding Cell
 * Adapter holds indexes of unfolded elements for correct work with default reusable views behavior
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class  FoldingCellListAdapter extends ArrayAdapter<Map<String, Integer>> {

  static final String KEY_LON_OUT = "lon_out";
  static final String KEY_LON_IN = "lon_in";
  static final String KEY_NUMBERS_OUT = "numbers";
  static final String KEY_NUMBERS_IN = "numbers_in";
  static final String KEY_IMAGE_IN = "image_in";
  static final String KEY_TEXT1OUT = "text1out";
  static final String KEY_TEXT1IN = "text1in";
  static final String KEY_TEXT1CONTENT = "text1content";
  static final String KEY_TEXT2CONTENT = "text2content";
  static final String KEY_TEXT3CONTENT = "text3content";

  private HashSet<Integer> unfoldedIndexes = new HashSet<>();
  private View.OnClickListener defaultRequestBtnClickListener;

  private final List<Map<String, Integer>> mData;

  public FoldingCellListAdapter(Context context, int layoutResourceId, List<Map<String,
          Integer>> data) {
    super(context, layoutResourceId, data);
    mData = data;
  }

  @NonNull
  @Override
  public View getView(int position, View convertView, @NonNull ViewGroup parent) {
    // get item for selected view

    // if cell is exists - reuse it, if not - create the new one from resource
    FoldingCell cell = (FoldingCell) convertView;
    ViewHolder viewHolder;
    if (cell == null) {
      viewHolder = new ViewHolder();
      LayoutInflater vi = LayoutInflater.from(getContext());
      cell = (FoldingCell) vi.inflate(R.layout.cell, parent, false);
      // binding view parts to view holder
      viewHolder.lon_out = cell.findViewById(R.id.lon_out);
      viewHolder.lon_in = cell.findViewById(R.id.lon_in);
      viewHolder.numbers_out = cell.findViewById(R.id.numbers_out);
      viewHolder.numbers_in = cell.findViewById(R.id.numbers_in);
      viewHolder.image_in = cell.findViewById(R.id.image_in);
      viewHolder.text1out = cell.findViewById(R.id.text1out);
      viewHolder.text1in = cell.findViewById(R.id.text1in);
      viewHolder.text1content = cell.findViewById(R.id.text1content);
      viewHolder.text2content = cell.findViewById(R.id.text2content);
      viewHolder.text3content = cell.findViewById(R.id.text3content);
      cell.setTag(viewHolder);
    } else {
      // for existing cell set valid valid state(without animation)
      if (unfoldedIndexes.contains(position)) {
        cell.unfold(true);
      } else {
        cell.fold(true);
      }
      viewHolder = (ViewHolder) cell.getTag();
    }




    viewHolder.lon_out.setBackgroundResource(mData.get(position).get(KEY_LON_OUT));
    viewHolder.lon_in.setBackgroundResource(mData.get(position).get(KEY_LON_IN));
    viewHolder.numbers_out.setText(mData.get(position).get(KEY_NUMBERS_OUT));
    viewHolder.numbers_in.setText(mData.get(position).get(KEY_NUMBERS_IN));
    viewHolder.image_in.setImageResource(mData.get(position).get(KEY_IMAGE_IN));
    viewHolder.text1out.setText(mData.get(position).get(KEY_TEXT1OUT));
    viewHolder.text1in.setText(mData.get(position).get(KEY_TEXT1IN));
    viewHolder.text1content.setText(mData.get(position).get(KEY_TEXT1CONTENT));
    viewHolder.text2content.setText(mData.get(position).get(KEY_TEXT2CONTENT));
    viewHolder.text3content.setText(mData.get(position).get(KEY_TEXT3CONTENT));

    return cell;
  }

  // simple methods for register cell state changes
  public void registerToggle(int position) {
    if (unfoldedIndexes.contains(position))
      registerFold(position);
    else
      registerUnfold(position);
  }

  public void registerFold(int position) {
    unfoldedIndexes.remove(position);
  }

  public void registerUnfold(int position) {
    unfoldedIndexes.add(position);
  }

  public View.OnClickListener getDefaultRequestBtnClickListener() {
    return defaultRequestBtnClickListener;
  }

  public void setDefaultRequestBtnClickListener(View.OnClickListener defaultRequestBtnClickListener) {
    this.defaultRequestBtnClickListener = defaultRequestBtnClickListener;
  }

  // View lookup cache
  private static class ViewHolder {
    RelativeLayout lon_out;
    RelativeLayout lon_in;
    TextView numbers_out;
    TextView numbers_in;
    ImageView image_in;
    TextView text1out;
    TextView text1in;
    TextView text1content;
    TextView text2content;
    TextView text3content;
  }
}