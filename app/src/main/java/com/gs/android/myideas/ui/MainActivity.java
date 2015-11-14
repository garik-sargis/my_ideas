package com.gs.android.myideas.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.gs.android.myideas.App;
import com.gs.android.myideas.R;
import com.gs.android.myideas.dagger.components.DaggerMainActivityComponent;
import com.gs.android.myideas.dagger.components.MainActivityComponent;
import com.gs.android.myideas.domain.Idea;
import com.gs.android.myideas.domain.interactor.IdeaCreator;
import com.gs.android.myideas.domain.interactor.IdeaListSource;
import com.gs.android.myideas.domain.interactor.IdeaSource;
import com.gs.android.myideas.ui.util.LayoutManagers;
import com.gs.android.myideas.ui.util.ViewFactories;
import com.gs.android.myideas.ui.util.ViewFactory;

import net._01001111.text.LoremIpsum;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.functions.Action1;
import rx.observers.Subscribers;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.list_ideas) RecyclerView mvIdeaList;

    @Bind(R.id.action_generateIdea) FloatingActionButton mvGenerateIdeaButton;

    @Bind(R.id.appBar) Toolbar mvAppBar;

    @Bind(R.id.drawer) DrawerLayout mvDrawer;


    @Inject IdeaCreator mIdeaCreator;

    @Inject IdeaSource mIdeaSource;

    @Inject IdeaListSource mIdeaListSource;

    public ActionBarDrawerToggle mDrawerToggle;

    private IdeaListAdapter mAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Dependency injection
        MainActivityComponent component = DaggerMainActivityComponent.builder()
                .appComponent(App.fromContext(this).component())
                .build();
        component.inject(this);

        // Content view init.
        setContentView(R.layout.activity_main);
        // View reference injection
        ButterKnife.bind(this);

        initNavigation();

        // Adapter
        ViewFactory viewFactory = ViewFactories.inflating(getLayoutInflater(),
                R.layout.item_idea_list);
        mAdapter = IdeaListAdapter.create(viewFactory, mIdeaSource);

        // Idea List (RecyclerView)
        mvIdeaList.setHasFixedSize(true);
        mvIdeaList.setLayoutManager(LayoutManagers.linear(this));
        mvIdeaList.setAdapter(mAdapter);

        mIdeaListSource.get(Subscribers.create(new Action1<List<Long>>() {
            @Override public void call(final List<Long> ids) {
                mAdapter.swapIds(ids);
            }
        }));

        // FAB
        mvGenerateIdeaButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(final View v) {
                generateIdea();
            }
        });
    }

    // TODO: Remove
    private LoremIpsum mLoremIpsum = new LoremIpsum();

    private void initNavigation() {
        setSupportActionBar(mvAppBar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        // TODO: Replace placeholder string resources
        mDrawerToggle = new ActionBarDrawerToggle(this,
                mvDrawer,
                mvAppBar,
                R.string.app_name,
                R.string.app_name);

        mvDrawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    // TODO: Replace with a proper functionality
    /**
     * Generates a random idea
     */
    private void generateIdea() {
        Timber.d("FAB clicked");
        final String text = mLoremIpsum.sentence();
        final Idea idea = new Idea(text);
        mIdeaCreator.create(
                Subscribers.create(new Action1<Boolean>() {
                    @Override public void call(final Boolean result) {
                        Timber.d("Insert result: %b", result);
                    }
                }),
                idea);
    }

    @Override
    public void onConfigurationChanged(final Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mvDrawer.isDrawerOpen(GravityCompat.START)) {
            mvDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.support.v7.appcompat.R.id.home:
                return mDrawerToggle.onOptionsItemSelected(item);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
