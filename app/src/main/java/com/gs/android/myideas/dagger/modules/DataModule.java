package com.gs.android.myideas.dagger.modules;

import com.gs.android.myideas.data.mock.MockIdeaRepo;
import com.gs.android.myideas.domain.repo.IdeaRepo;

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
