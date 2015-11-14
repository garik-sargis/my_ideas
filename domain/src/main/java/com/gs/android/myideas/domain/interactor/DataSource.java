package com.gs.android.myideas.domain.interactor;

import com.gs.android.myideas.domain.WithId;

import rx.Subscriber;

public interface DataSource<T> {

    void subscribe(Subscriber<WithId<T>> subscriber, long id);

}
