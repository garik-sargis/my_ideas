package com.gs.android.myideas.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gs.android.myideas.domain.Idea;

import butterknife.ButterKnife;

class IdeaViewHolder extends RecyclerView.ViewHolder {
    private final TextView mvText;

    private Idea mData;

    public IdeaViewHolder(View itemView) {
        super(itemView);

        mvText = ButterKnife.findById(itemView, android.R.id.text1);
    }

    public void setData(final Idea data) {
        mData = data;

        // TODO: Move out?
        mvText.setText(data.text());
    }

    public Idea getData() {
        return mData;
    }

    public void clearData() {
        mData = null;
    }

    public boolean hasData() {
        return mData != null;
    }
}
