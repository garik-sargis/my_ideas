package com.gs.android.mythoughts.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.gs.android.mythoughts.R;
import com.gs.android.mythoughts.db.mock.MockIdeaRepo;
import com.gs.android.mythoughts.domain.Idea;
import com.gs.android.mythoughts.domain.interactor.Connector;
import com.gs.android.mythoughts.domain.interactor.IdeaListSource;
import com.gs.android.mythoughts.domain.interactor.IdeaCreator;
import com.gs.android.mythoughts.domain.interactor.IdeaSource;
import com.gs.android.mythoughts.ui.util.InflatingViewFactory;
import com.gs.android.mythoughts.ui.util.MarginItemDecoration;
import com.gs.android.mythoughts.ui.util.ViewFactory;

import net._01001111.text.LoremIpsum;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.observers.Subscribers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.list_ideas) RecyclerView mvIdeaList;

    @Bind(R.id.action_generateIdea) FloatingActionButton mvGenerateIdeaButton;

    @Bind(R.id.appBar) Toolbar mvAppBar;

    @Bind(R.id.drawer) DrawerLayout mvDrawer;

    public ActionBarDrawerToggle mDrawerToggle;

    private IdeaListAdapter mAdapter;

    private IdeaCreator mIdeaCreator;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initNavigation();

        mvIdeaList.setHasFixedSize(true);

        LayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);

        mvIdeaList.setLayoutManager(layoutManager);
        final float margin = getResources().getDimension(R.dimen.card_grid_margin);
//        mvIdeaList.addItemDecoration(MarginItemDecoration.create(margin));

        // Dependencies
        MockIdeaRepo mockIdeaRepo = new MockIdeaRepo();
        ExecutorService ioExecutor = Executors.newSingleThreadExecutor();
        Scheduler ioScheduler = Schedulers.from(ioExecutor);
        Connector connector = new Connector(ioScheduler, AndroidSchedulers.mainThread());
        IdeaSource ideaSource = new IdeaSource(mockIdeaRepo, connector);

        IdeaListSource ideaListSource = new IdeaListSource(mockIdeaRepo, connector);
        ideaListSource.get(Subscribers.create(new Action1<List<Long>>() {
            @Override public void call(final List<Long> ids) {
                mAdapter.swapIds(ids);
            }
        }));

        // Adapter
        ViewFactory viewFactory = new InflatingViewFactory(getLayoutInflater(),
                android.R.layout.simple_list_item_1);
        mAdapter = IdeaListAdapter.create(viewFactory, ideaSource);
        mvIdeaList.setAdapter(mAdapter);

        // FAB
        mIdeaCreator = new IdeaCreator(mockIdeaRepo, connector);

        mvGenerateIdeaButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(final View v) {
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

    @Override public void onConfigurationChanged(final Configuration newConfig) {
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
