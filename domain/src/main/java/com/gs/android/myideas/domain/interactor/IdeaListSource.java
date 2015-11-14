package com.gs.android.myideas.domain.interactor;

import com.gs.android.myideas.domain.repo.IdeaRepo;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class IdeaListSource {
    private final IdeaRepo mRepo;

    private final Connector mConnector;

    public IdeaListSource(IdeaRepo repo, Connector connector) {
        mRepo = repo;
        mConnector = connector;
    }

    public void get(Subscriber<List<Long>> subscriber) {
        Observable<List<Long>> observable = mRepo.queryAll();
        mConnector.connect(observable, subscriber);
    }


}
