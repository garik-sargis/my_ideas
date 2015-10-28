package com.gs.android.mythoughts.domain.interactor;

import com.gs.android.mythoughts.domain.Idea;
import com.gs.android.mythoughts.domain.repo.IdeaRepo;

import rx.Observable;
import rx.Subscriber;

public class IdeaCreator {
    private final IdeaRepo mRepo;

    private final Connector mConnector;

    public IdeaCreator(IdeaRepo repo, Connector connector) {
        mRepo = repo;
        mConnector = connector;
    }

    public void create(Subscriber<Boolean> subscriber, Idea idea) {
        Observable<Boolean> observable = mRepo.insert(idea);
        mConnector.connect(observable, subscriber);
    }
}
