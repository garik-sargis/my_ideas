package com.gs.android.myideas.domain.repo;

import com.gs.android.myideas.domain.WithId;

import java.util.List;

import rx.Observable;

public interface IdeaRepo {
    Observable<Boolean> insert(com.gs.android.myideas.domain.Idea idea);

    Observable<List<Long>> queryAll();

    Observable<WithId<com.gs.android.myideas.domain.Idea>> query(long id);

    // TODO: Change return type?
    Observable<Boolean> update(long id, com.gs.android.myideas.domain.Idea idea);
}
