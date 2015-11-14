package com.gs.android.myideas.dagger.components;

import com.gs.android.myideas.dagger.modules.AppModule;
import com.gs.android.myideas.dagger.modules.DataModule;
import com.gs.android.myideas.dagger.modules.ExecutionModule;
import com.gs.android.myideas.dagger.modules.DomainModule;
import com.gs.android.myideas.domain.interactor.IdeaCreator;
import com.gs.android.myideas.domain.interactor.IdeaListSource;
import com.gs.android.myideas.domain.interactor.IdeaSource;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ExecutionModule.class, DataModule.class, DomainModule.class, AppModule.class})
public interface AppComponent {
    IdeaSource ideaSource();

    IdeaListSource ideaListSource();

    IdeaCreator ideaCreator();
}
