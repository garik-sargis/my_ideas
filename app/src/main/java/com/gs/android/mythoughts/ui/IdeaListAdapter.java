package com.gs.android.mythoughts.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.gs.android.mythoughts.domain.Idea;
import com.gs.android.mythoughts.domain.Ideas;
import com.gs.android.mythoughts.domain.WithId;
import com.gs.android.mythoughts.domain.interactor.IdeaSource;
import com.gs.android.mythoughts.ui.util.SubscriberPool;
import com.gs.android.mythoughts.ui.util.ViewFactory;

import java.util.Collections;
import java.util.List;

import rx.Subscriber;

// TODO: Use staggered view with fixed-size items
// TODO: IDs of type long are not generic.
// TODO: Receiving a potentially infinite list of IDs.
// TODO: Consider the case when the data associated with a signle item comes from multiple sources.
public class IdeaListAdapter extends RecyclerView.Adapter<IdeaViewHolder> implements SubscriberPool.Listener<IdeaViewHolder,WithId<Idea>> {

    // TODO: Refactor default/error data

    private static final Idea DEFAULT_DATA = Ideas.with("Loading...");
    private static final Idea ERROR_DATA = Ideas.with("ERROR!!!");

    public static IdeaListAdapter create(final ViewFactory itemViewFactory,
                                         final IdeaSource ideaSource) {
        return new IdeaListAdapter(itemViewFactory, ideaSource, Collections.<Long>emptyList());
    }

    private final IdeaSource mIdeaSource;

    private final ViewFactory mItemViewFactory;

    private List<Long> mIds;

    private final SubscriberPool<IdeaViewHolder, WithId<Idea>> mSubscriberPool;

    public IdeaListAdapter(@NonNull final ViewFactory itemViewFactory,
                           @NonNull final IdeaSource ideaSource,
                           @NonNull final List<Long> ids) {
        mItemViewFactory = itemViewFactory;
        mIdeaSource = ideaSource;
        mIds = ids;

        mSubscriberPool = SubscriberPool.create(this);

        setHasStableIds(true);
    }

    public void swapIds(@NonNull final List<Long> ids) {
        mIds = ids;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(final int position) {
        return mIds.get(position);
    }

    @Override
    public IdeaViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View itemView = mItemViewFactory.create(parent);
        return new IdeaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final IdeaViewHolder holder, final int position) {
        final long id = getItemId(position);

        // Use default data before real data arrives
        holder.setData(DEFAULT_DATA);

        // Get a new subscriber from the pool
        Subscriber<WithId<Idea>> subscriber = mSubscriberPool.get(holder);

        // Subscribe to get the data associated with the ID
        mIdeaSource.subscribe(subscriber, id);
    }

    @Override
    public void onViewRecycled(final IdeaViewHolder holder) {
        // Unsubscribe the old subscriber
        mSubscriberPool.unsubscribe(holder);
    }

    @Override
    public int getItemCount() {
        return mIds.size();
    }

    @Override public void onDataReceived(final IdeaViewHolder holder, final WithId<Idea> data) {
        holder.setData(data.content());
    }
}
