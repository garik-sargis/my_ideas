package com.gs.android.myideas;

import android.app.Application;
import android.content.Context;

import com.gs.android.myideas.dagger.components.AppComponent;
import com.gs.android.myideas.dagger.components.DaggerAppComponent;
import com.gs.android.myideas.dagger.modules.AppModule;
import com.gs.android.myideas.dagger.modules.DataModule;
import com.gs.android.myideas.dagger.modules.ExecutionModule;
import com.gs.android.myideas.dagger.modules.DomainModule;
import com.gs.android.myideas.log.ThreadDebugTree;

import timber.log.Timber;

public class App extends Application {

    private AppComponent mComponent;

    @Override public void onCreate() {
        super.onCreate();

        // TODO: Plant only in debug build-type
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
