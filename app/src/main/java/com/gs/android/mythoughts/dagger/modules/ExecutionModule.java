package com.gs.android.mythoughts.dagger.modules;

import com.gs.android.mythoughts.dagger.qualifiers.IoScheduler;
import com.gs.android.mythoughts.dagger.qualifiers.UiScheduler;

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
    public @IoScheduler Scheduler provideIoScheduler() {
        ExecutorService ioExecutor = Executors.newSingleThreadExecutor();
        return Schedulers.from(ioExecutor);
    }

    @Provides @Singleton
    public @UiScheduler Scheduler provideUiScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
