package com.ess.filepicker.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * UiUtils
 * Created by TU on 2019/7/2.
 */

public class UiUtils {

    /***
     * DP 转 PX
     * @param c
     * @param dipValue
     * @return
     */
    public static int dpToPx(Context c, float dipValue) {
        DisplayMetrics metrics = c.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }

    public static int getImageResize(Context context, RecyclerView recyclerView) {
        int mImageResize;
        RecyclerView.LayoutManager lm = recyclerView.getLayoutManager();
        int spanCount = ((GridLayoutManager) lm).getSpanCount();
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        mImageResize = screenWidth / spanCount;
        return mImageResize;
    }

}
