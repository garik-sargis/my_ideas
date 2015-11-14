package com.gs.android.myideas.dagger.modules;

import com.gs.android.myideas.dagger.Qualifiers;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

// TODO: Consider usages from multiple execution contexts
@Module
public final class DomainModule {
    // TODO: Singleton or not?
    @Provides @Singleton
    public com.gs.android.myideas.domain.interactor.IdeaSource provideIdeaSource(final com.gs.android.myideas.domain.repo.IdeaRepo ideaRepo, final com.gs.android.myideas.domain.interactor.Connector connector) {
        return new com.gs.android.myideas.domain.interactor.IdeaSource(ideaRepo, connector);
    }
    // TODO: Singleton or not?
    @Provides @Singleton
    public com.gs.android.myideas.domain.interactor.IdeaListSource provideIdeaListSource(final com.gs.android.myideas.domain.repo.IdeaRepo ideaRepo, final com.gs.android.myideas.domain.interactor.Connector connector) {
        return new com.gs.android.myideas.domain.interactor.IdeaListSource(ideaRepo, connector);
    }

    // TODO: Singleton or not?
    @Provides @Singleton
    public com.gs.android.myideas.domain.interactor.IdeaCreator provideIdeaCreator(final com.gs.android.myideas.domain.repo.IdeaRepo ideaRepo, final com.gs.android.myideas.domain.interactor.Connector connector) {
        return new com.gs.android.myideas.domain.interactor.IdeaCreator(ideaRepo, connector);
    }

    // TODO: Singleton or not?
    @Provides @Singleton
    public com.gs.android.myideas.domain.interactor.Connector provideConnector(final @Qualifiers.IoScheduler Scheduler ioScheduler,
                                      final @Qualifiers.UiScheduler Scheduler uiScheduler) {
        return new com.gs.android.myideas.domain.interactor.Connector(ioScheduler, uiScheduler);
    }
}
