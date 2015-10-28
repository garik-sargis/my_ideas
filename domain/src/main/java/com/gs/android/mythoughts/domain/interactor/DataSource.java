package com.gs.android.mythoughts.domain.interactor;

import com.gs.android.mythoughts.domain.WithId;

import rx.Subscriber;

public interface DataSource<T> {

    void getIdea(Subscriber<WithId<T>> subscriber, long id);

}
