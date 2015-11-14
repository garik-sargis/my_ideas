package com.gs.android.myideas.dagger.modules;

import com.gs.android.myideas.dagger.Qualifiers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public final class ExecutionModule {
    @Provides @Singleton
    public @Qualifiers.IoScheduler Scheduler provideIoScheduler() {
        ExecutorService ioExecutor = Executors.newSingleThreadExecutor();
        return Schedulers.from(ioExecutor);
    }

    @Provides @Singleton
    public @Qualifiers.UiScheduler Scheduler provideUiScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
