package com.gs.android.myideas.ui.util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MarginDecoration extends RecyclerView.ItemDecoration {

    public static MarginDecoration create(final float left, final float top,
                                          final float right, final float bottom) {
        return new MarginDecoration(left, top, right, bottom);
    }

    public static MarginDecoration create(final float margin) {
        return new MarginDecoration(margin, margin, margin, margin);
    }

    private final float mMarginLeft;
    private final float mMarginTop;
    private final float mMarginRight;
    private final float mMarginBottom;

    public MarginDecoration(final float marginLeft, final float marginTop, final float marginRight, final float marginBottom) {
        mMarginLeft = marginLeft;
        mMarginTop = marginTop;
        mMarginRight = marginRight;
        mMarginBottom = marginBottom;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.left = Math.round(mMarginLeft);
        outRect.top = Math.round(mMarginTop);
        outRect.right = Math.round(mMarginRight);
        outRect.bottom = Math.round(mMarginBottom);
    }
}
