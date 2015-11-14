package com.gs.android.mythoughts;

import android.app.Application;
import android.content.Context;

import com.gs.android.mythoughts.dagger.components.AppComponent;
import com.gs.android.mythoughts.dagger.components.DaggerAppComponent;
import com.gs.android.mythoughts.dagger.modules.AppModule;
import com.gs.android.mythoughts.dagger.modules.DataModule;
import com.gs.android.mythoughts.dagger.modules.DomainModule;
import com.gs.android.mythoughts.dagger.modules.ExecutionModule;
import com.gs.android.mythoughts.log.ThreadDebugTree;

import timber.log.Timber;

public class App extends Application {

    private AppComponent mComponent;

    @Override public void onCreate() {
        super.onCreate();

        Timber.plant(new ThreadDebugTree());

        mComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .executionModule(new ExecutionModule())
                .dataModule(new DataModule())
                .domainModule(new DomainModule())
                .build();
    }

    public AppComponent component() {
        return mComponent;
    }

    public static App fromContext(final Context context) {
        return ((App) context.getApplicationContext());
    }
}
