package com.gs.android.mythoughts.dagger.modules;

import com.gs.android.mythoughts.dagger.qualifiers.IoScheduler;
import com.gs.android.mythoughts.dagger.qualifiers.UiScheduler;
import com.gs.android.mythoughts.domain.interactor.Connector;
import com.gs.android.mythoughts.domain.interactor.IdeaCreator;
import com.gs.android.mythoughts.domain.interactor.IdeaListSource;
import com.gs.android.mythoughts.domain.interactor.IdeaSource;
import com.gs.android.mythoughts.domain.repo.IdeaRepo;

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
    public IdeaListSource provideIdeaListSource(final IdeaRepo ideaRepo, final Connector connector) {
        return new IdeaListSource(ideaRepo, connector);
    }

    // TODO: Singleton or not?
    @Provides @Singleton
    public IdeaCreator provideIdeaCreator(final IdeaRepo ideaRepo, final Connector connector) {
        return new IdeaCreator(ideaRepo, connector);
    }

    // TODO: Singleton or not?
    @Provides @Singleton
    public Connector provideConnector(final @IoScheduler Scheduler ioScheduler,
                                      final @UiScheduler Scheduler uiScheduler) {
        return new Connector(ioScheduler, uiScheduler);
    }
}
