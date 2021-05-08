package com.zaker.android.sapeh.app.main.sweetalert;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.StateListDrawable;
import android.view.View;

public class ViewUtils {

    static Drawable[] getDrawable(View view) {
        StateListDrawable drawable = (StateListDrawable) view.getBackground();
        DrawableContainer.DrawableContainerState dcs = (DrawableContainer.DrawableContainerState) drawable.getConstantState();
        if (dcs != null) {
            return dcs.getChildren();
        }
        return null;
    }

}