package com.gs.android.mythoughts.domain.interactor;

import com.gs.android.mythoughts.domain.Idea;
import com.gs.android.mythoughts.domain.WithId;
import com.gs.android.mythoughts.domain.repo.IdeaRepo;

import rx.Observable;
import rx.Subscriber;

public class IdeaSource implements DataSource<Idea> {
    private final IdeaRepo mRepo;

    private final Connector mConnector;

    public IdeaSource(IdeaRepo repo, final Connector connector) {
        mRepo = repo;
        mConnector = connector;
    }

    public void getIdea(Subscriber<WithId<Idea>> subscriber, long id) {
        Observable<WithId<Idea>> observable = mRepo.query(id);
        mConnector.connect(observable, subscriber);
    }
}
