package com.gs.android.myideas.data.db;

import android.database.Cursor;

import com.gs.android.myideas.domain.Idea;
import com.gs.android.myideas.domain.WithId;

import rx.Observable;
import rx.Subscriber;

public class ObservableIdeaCursor {

    public static ObservableIdeaCursor from(Cursor cursor) {
        return new ObservableIdeaCursor(cursor);
    }

    private final Cursor cursor;

    private ObservableIdeaCursor(Cursor cursor) {
        this.cursor = cursor;
    }

    public Observable<WithId<Idea>> get(final int position) {
        Observable.create(new Observable.OnSubscribe<WithId<Idea>>() {
            @Override public void call(Subscriber<? super WithId<Idea>> subscriber) {
                WithId<Idea> ideaWithId = IdeaSqlConverter.fromCursor(cursor, position);
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(ideaWithId);
                }
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
            }
        });
        return null;
    }
}
