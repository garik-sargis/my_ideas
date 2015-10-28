package com.gs.android.mythoughts.ui.util;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InflatingViewFactory implements ViewFactory {

    @LayoutRes private final int mLayoutResId;
    private final LayoutInflater mInflater;

    public InflatingViewFactory(@NonNull LayoutInflater inflater,
                                @LayoutRes final int layoutResId) {
        mInflater = inflater;
        mLayoutResId = layoutResId;
    }

    @Override
    public View create(@NonNull final ViewGroup parent) {
        return mInflater.inflate(mLayoutResId, parent, false);
    }
}
