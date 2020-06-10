package com.dimeno.adapter.footer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimeno.adapter.R;
import com.dimeno.adapter.callback.OnLoadMoreCallback;
import com.dimeno.adapter.meta.LoadMoreState;

/**
 * LoadMoreFooter
 * Created by wangzhen on 2020/6/10.
 */
public class LoadMoreFooter implements View.OnAttachStateChangeListener, View.OnClickListener {
    private OnLoadMoreCallback mCallback;
    private View mContainerLoading;
    private View mContainerNoMore;
    private int mState = LoadMoreState.LOADING;
    private View mContainerError;

    public LoadMoreFooter(OnLoadMoreCallback callback) {
        this.mCallback = callback;
    }

    public View onCreateView(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_load_more_layout, parent, false);
        onViewCreated(itemView);
        return itemView;
    }

    private void onViewCreated(View itemView) {
        mContainerLoading = itemView.findViewById(R.id.container_loading);
        mContainerNoMore = itemView.findViewById(R.id.container_no_more);
        mContainerError = itemView.findViewById(R.id.container_error);
        mContainerError.setOnClickListener(this);
        itemView.addOnAttachStateChangeListener(this);
    }

    @Override
    public void onViewAttachedToWindow(View v) {
        if (mState == LoadMoreState.LOADING) {
            loadMore();
        }
    }

    private void loadMore() {
        setState(LoadMoreState.LOADING);
        if (mCallback != null) {
            mCallback.onLoadMore();
        }
    }

    public void setState(@LoadMoreState int state) {
        mState = state;
        mContainerLoading.setVisibility(mState == LoadMoreState.LOADING ? View.VISIBLE : View.GONE);
        mContainerNoMore.setVisibility(mState == LoadMoreState.NO_MORE ? View.VISIBLE : View.GONE);
        mContainerError.setVisibility(mState == LoadMoreState.ERROR ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onViewDetachedFromWindow(View v) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.container_error) {
            loadMore();
        }
    }
}
