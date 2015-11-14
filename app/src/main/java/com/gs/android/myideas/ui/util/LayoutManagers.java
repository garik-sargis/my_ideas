package com.gs.android.myideas.ui.util;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * A collection of {@link RecyclerView.LayoutManager} factory methods.
 */
public final class LayoutManagers {
    /**
     * Do not instantiate!
     */
    private LayoutManagers() {
        // Empty
    }

    public static LinearLayoutManager linear(final Context context) {
        return new LinearLayoutManager(context);
    }
}
