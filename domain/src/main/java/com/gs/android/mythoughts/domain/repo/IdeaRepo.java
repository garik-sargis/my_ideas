package com.gs.android.mythoughts.domain.repo;

import com.gs.android.mythoughts.domain.Idea;
import com.gs.android.mythoughts.domain.WithId;

import java.util.List;

import rx.Observable;

public interface IdeaRepo {
    Observable<Boolean> insert(Idea idea);

    Observable<List<Long>> queryAll();

    Observable<WithId<Idea>> query(long id);

    // TODO: Change return type?
    Observable<Boolean> update(long id, Idea idea);
}
