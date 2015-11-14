package com.gs.android.myideas.ui.util;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public final class ViewFactories {

    /**
     * Do not instantiate!
     */
    private ViewFactories() {
        // Empty
    }

    public static ViewFactory inflating(@NonNull final LayoutInflater inflater,
                                 @LayoutRes final int layoutResId) {
        return new ViewFactory() {
            @Override public View create(@NonNull final ViewGroup parent) {
                return inflater.inflate(layoutResId, parent, false);
            }
        };
    }
}
