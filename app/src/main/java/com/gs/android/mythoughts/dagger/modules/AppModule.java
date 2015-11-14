package com.gs.android.mythoughts.dagger.modules;

import com.gs.android.mythoughts.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class AppModule {
    private final App mApp;

    public AppModule(final App app) {
        mApp = app;
    }

    @Provides @Singleton
    public App provideApp() {
        return mApp;
    }

}
