package com.gs.android.mythoughts;

import android.app.Application;

import com.gs.android.mythoughts.log.ThreadDebugTree;

import timber.log.Timber;

public class App extends Application {

    @Override public void onCreate() {
        super.onCreate();

        Timber.plant(new ThreadDebugTree());
    }
}
