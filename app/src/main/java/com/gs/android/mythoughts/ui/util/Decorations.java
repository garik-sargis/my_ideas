package com.gs.android.mythoughts.ui.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;

public final class Decorations {
    /**
     * Do not instantiate!
     */
    private Decorations() {

    }

    public static ItemDecoration divider(final Context context, final int orientation) {
        return new DividerDecoration(context, orientation);
    }
}
