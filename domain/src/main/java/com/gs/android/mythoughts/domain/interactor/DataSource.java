package com.gs.android.mythoughts.domain.interactor;

import com.gs.android.mythoughts.domain.WithId;

import rx.Subscriber;

public interface DataSource<T> {

    void subscribe(Subscriber<WithId<T>> subscriber, long id);

}
