package com.zaker.android.sapeh.app.main.activitylist;

import android.content.res.TypedArray;
import android.util.TypedValue;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.zaker.android.sapeh.R;

public abstract class BaseActivity extends LocalizationActivity {


    protected int getActionBarSize() {
        TypedValue typedValue = new TypedValue();
        int[] textSizeAttr = new int[]{R.attr.actionBarSize};
        int indexOfAttrTextSize = 0;
        TypedArray a = obtainStyledAttributes(typedValue.data, textSizeAttr);
        int actionBarSize = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
        a.recycle();
        return actionBarSize;
    }

    protected int getScreenHeight() {
        return findViewById(android.R.id.content).getHeight();
    }


}
