package com.gs.android.data.db.mock;

import com.gs.android.mythoughts.domain.Idea;
import com.gs.android.mythoughts.domain.WithId;
import com.gs.android.mythoughts.domain.repo.IdeaRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.subjects.BehaviorSubject;

public class MockIdeaRepo implements IdeaRepo {

    private final Map<Long, Idea> mMap;

    private final BehaviorSubject<List<Long>> mSubject;

    private long mMaxId;

    public MockIdeaRepo() {
        mMap = new HashMap<>();
        mSubject = BehaviorSubject.create(createIdList());
        mMaxId = 0L;
    }

    private List<Long> createIdList() {
        final ArrayList<Map.Entry<Long, Idea>> list = new ArrayList<>(mMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Long, Idea>>() {
            @Override
            public int compare(final Map.Entry<Long, Idea> lhs, final Map.Entry<Long, Idea> rhs) {
                return lhs.getValue().text().compareTo(rhs.getValue().text());
            }
        });

        final ArrayList<Long> idList = new ArrayList<>();
        for (final Map.Entry<Long, Idea> entry : list) {
            idList.add(entry.getKey());
        }
        return idList;
    }

    @Override
    public Observable<List<Long>> queryAll() {
        return mSubject;
    }

    @Override
    public Observable<WithId<Idea>> query(final long id) {
        return Observable.create(new Observable.OnSubscribe<WithId<Idea>>() {
            @Override public void call(final Subscriber<? super WithId<Idea>> sub) {
                // TODO: Remove sleep
                try {
                    Thread.sleep(300L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Idea idea = mMap.get(id);
                WithId<Idea> ideaWithId = new WithId<>(id, idea);
                sub.onNext(ideaWithId);
                sub.onCompleted();
            }
        });
    }

    @Override
    public Observable<Boolean> insert(final Idea idea) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override public void call(final Subscriber<? super Boolean> sub) {
                mMaxId++;
                mMap.put(mMaxId, idea);

                sub.onNext(true);
                sub.onCompleted();

                mSubject.onNext(createIdList());
            }
        });
    }

    @Override
    public Observable<Boolean> update(final long id, final Idea idea) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override public void call(final Subscriber<? super Boolean> sub) {
                mMap.put(id, idea);

                sub.onNext(true);
                sub.onCompleted();

                mSubject.onNext(createIdList());
            }
        });
    }
}
