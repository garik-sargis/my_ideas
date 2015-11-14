package com.gs.android.myideas.dagger.components;

import com.gs.android.myideas.dagger.Scopes;
import com.gs.android.myideas.ui.MainActivity;

import dagger.Component;

@Scopes.PerActivity
@Component(dependencies = {AppComponent.class})
public interface MainActivityComponent {
    MainActivity inject(MainActivity mainActivity);
}
