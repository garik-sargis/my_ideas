package com.gs.android.mythoughts.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gs.android.mythoughts.R;
import com.gs.android.mythoughts.domain.Idea;
import com.gs.android.mythoughts.domain.WithId;
import com.gs.android.mythoughts.domain.interactor.IdeaSource;
import com.gs.android.mythoughts.ui.util.ViewFactory;

import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import rx.Subscriber;
import rx.functions.Action1;
import rx.observers.Subscribers;
import timber.log.Timber;

public class IdeaListAdapter extends RecyclerView.Adapter<IdeaListAdapter.ViewHolder> {

    public interface OnClickListener {
        void onClick(long id);
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mvText;

        private Subscriber<WithId<Idea>> mSubscriber;

        public ViewHolder(View itemView) {
            super(itemView);

            mvText = ButterKnife.findById(itemView, android.R.id.text1);
        }

        private void bind(Idea data) {
            mvText.setText(data.text());
        }

        public Subscriber<WithId<Idea>> swapSubscriber() {
            unsubscribe();

            mSubscriber = Subscribers.create(new Action1<WithId<Idea>>() {
                @Override public void call(final WithId<Idea> ideaWithId) {
                    ViewHolder.this.bind(ideaWithId.data());
                }
            }, new Action1<Throwable>() {
                @Override public void call(final Throwable e) {
                    Timber.d("onError()");
                    e.printStackTrace();
                }
            });

            return mSubscriber;
        }

        public void unsubscribe() {
            if (mSubscriber != null) {
                mSubscriber.unsubscribe();
            }
        }
    }

    public static IdeaListAdapter create(final ViewFactory itemViewFactory,
                                         final IdeaSource ideaSource) {
        return new IdeaListAdapter(itemViewFactory, ideaSource, Collections.<Long>emptyList());
    }

    private final IdeaSource mIdeaSource;

    private final ViewFactory mItemViewFactory;

    private List<Long> mIds;

    public IdeaListAdapter(final ViewFactory itemViewFactory,
                           final IdeaSource ideaSource,
                           @NonNull final List<Long> ids) {
        mItemViewFactory = itemViewFactory;
        mIdeaSource = ideaSource;
        mIds = ids;

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
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View itemView = mItemViewFactory.create(parent);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final long id = getItemId(position);
        mIdeaSource.getIdea(holder.swapSubscriber(), id);
    }

    @Override
    public void onViewRecycled(final ViewHolder holder) {
        holder.unsubscribe();
    }

    @Override
    public int getItemCount() {
        return mIds.size();
    }
}
