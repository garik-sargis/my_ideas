package com.gs.android.myideas.domain.interactor;

import com.gs.android.myideas.domain.WithId;
import com.gs.android.myideas.domain.repo.IdeaRepo;

import rx.Observable;
import rx.Subscriber;

public class IdeaSource implements DataSource<com.gs.android.myideas.domain.Idea> {
    private final IdeaRepo mRepo;

    private final Connector mConnector;

    public IdeaSource(IdeaRepo repo, final Connector connector) {
        mRepo = repo;
        mConnector = connector;
    }

    public void subscribe(Subscriber<WithId<com.gs.android.myideas.domain.Idea>> subscriber, long id) {
        Observable<WithId<com.gs.android.myideas.domain.Idea>> observable = mRepo.query(id);
        mConnector.connect(observable, subscriber);
    }
}
