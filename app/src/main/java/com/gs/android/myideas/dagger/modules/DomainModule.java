package com.gs.android.myideas.dagger.modules;

import com.gs.android.myideas.dagger.Qualifiers;
import com.gs.android.myideas.domain.interactor.Connector;
import com.gs.android.myideas.domain.interactor.IdeaCreator;
import com.gs.android.myideas.domain.interactor.IdeaListSource;
import com.gs.android.myideas.domain.interactor.IdeaSource;
import com.gs.android.myideas.domain.repo.IdeaRepo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

// TODO: Consider usages from multiple execution contexts
@Module
public final class DomainModule {
    // TODO: Singleton or not?
    @Provides @Singleton
    public IdeaSource provideIdeaSource(final IdeaRepo ideaRepo, final Connector connector) {
        return new IdeaSource(ideaRepo, connector);
    }

    // TODO: Singleton or not?
    @Provides @Singleton
    public IdeaListSource provideIdeaListSource(final com.gs.android.myideas.domain.repo.IdeaRepo ideaRepo, final com.gs.android.myideas.domain.interactor.Connector connector) {
        return new IdeaListSource(ideaRepo, connector);
    }

    // TODO: Singleton or not?
    @Provides @Singleton
    public IdeaCreator provideIdeaCreator(final com.gs.android.myideas.domain.repo.IdeaRepo ideaRepo, final com.gs.android.myideas.domain.interactor.Connector connector) {
        return new IdeaCreator(ideaRepo, connector);
    }

    // TODO: Singleton or not?
    @Provides @Singleton
    public Connector provideConnector(final @Qualifiers.IoScheduler Scheduler ioScheduler,
                                      final @Qualifiers.UiScheduler Scheduler uiScheduler) {
        return new Connector(ioScheduler, uiScheduler);
    }
}
