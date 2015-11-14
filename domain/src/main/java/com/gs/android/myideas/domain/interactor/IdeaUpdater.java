package com.gs.android.myideas.domain.interactor;

import com.gs.android.myideas.domain.repo.IdeaRepo;

import rx.Observable;
import rx.Subscriber;

public class IdeaUpdater {
    private final IdeaRepo mRepo;

    private final Connector mConnector;

    public IdeaUpdater(IdeaRepo repo, Connector connector) {
        mRepo = repo;
        mConnector = connector;
    }

    public void update(Subscriber<Boolean> subscriber, long id, com.gs.android.myideas.domain.Idea idea) {
        Observable<Boolean> observable = mRepo.update(id, idea);
        mConnector.connect(observable, subscriber);
    }
}
