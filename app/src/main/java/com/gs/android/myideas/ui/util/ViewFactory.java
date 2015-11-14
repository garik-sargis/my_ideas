package com.gs.android.myideas.ui.util;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

public interface ViewFactory {
    View create(@NonNull ViewGroup parent);
}
