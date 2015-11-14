package com.gs.android.mythoughts.dagger.modules;

import com.gs.android.data.db.mock.MockIdeaRepo;
import com.gs.android.mythoughts.domain.repo.IdeaRepo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class DataModule {
    @Provides @Singleton
    public IdeaRepo provideIdeaRepo() {
        return new MockIdeaRepo();
    }
}
