package com.gs.android.myideas.domain.interactor;

import rx.Observable;
import rx.Subscriber;

// TODO: Rename (e.g. IdeaSink)
public class IdeaCreator {
    private final com.gs.android.myideas.domain.repo.IdeaRepo mRepo;

    private final Connector mConnector;

    public IdeaCreator(com.gs.android.myideas.domain.repo.IdeaRepo repo, Connector connector) {
        mRepo = repo;
        mConnector = connector;
    }

    public void create(Subscriber<Boolean> subscriber, com.gs.android.myideas.domain.Idea idea) {
        Observable<Boolean> observable = mRepo.insert(idea);
        mConnector.connect(observable, subscriber);
    }
}
