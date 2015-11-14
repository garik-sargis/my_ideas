package com.gs.android.mythoughts.dagger.components;

import com.gs.android.mythoughts.dagger.modules.AppModule;
import com.gs.android.mythoughts.dagger.modules.DataModule;
import com.gs.android.mythoughts.dagger.modules.DomainModule;
import com.gs.android.mythoughts.dagger.modules.ExecutionModule;
import com.gs.android.mythoughts.domain.interactor.IdeaCreator;
import com.gs.android.mythoughts.domain.interactor.IdeaListSource;
import com.gs.android.mythoughts.domain.interactor.IdeaSource;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ExecutionModule.class, DataModule.class, DomainModule.class, AppModule.class})
public interface AppComponent {
    IdeaSource ideaSource();

    IdeaListSource ideaListSource();

    IdeaCreator ideaCreator();
}
