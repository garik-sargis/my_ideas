package com.gs.android.myideas.domain.interactor;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;

public class Connector {
    // TODO: Change names?
    private final Scheduler mRepoScheduler;
    private final Scheduler mResponseScheduler;

    public Connector(final Scheduler repoScheduler,
                     final Scheduler responseScheduler) {
        mRepoScheduler = repoScheduler;
        mResponseScheduler = responseScheduler;
    }

    public <T> void connect(final Observable<T> observable,
                            final Subscriber<T> subscriber) {
        observable.subscribeOn(mRepoScheduler)
                .observeOn(mResponseScheduler)
                .subscribe(subscriber);
    }
}
