package com.dimeno.adapter.footer;

import android.util.Log;
import android.view.View;

import com.dimeno.adapter.annotation.LoadMoreState;
import com.dimeno.adapter.base.RecyclerItem;
import com.dimeno.adapter.callback.OnLoadMoreCallback;

/**
 * load more parent footer
 * Created by wangzhen on 2020/6/10.
 */
public abstract class LoadMoreFooter extends RecyclerItem implements View.OnAttachStateChangeListener {
    private OnLoadMoreCallback mCallback;
    private int mState = LoadMoreState.READY;
    private boolean isReady = true;
    private View itemView;

    public LoadMoreFooter(OnLoadMoreCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public void onViewCreated(View itemView) {
        this.itemView = itemView;
        this.itemView.addOnAttachStateChangeListener(this);
        onFooterViewCreated(itemView);
    }

    /**
     * handle footer views here
     *
     * @param itemView itemView
     */
    public abstract void onFooterViewCreated(View itemView);

    /**
     * update state here
     *
     * @param state state
     */
    public abstract void onStateChange(@LoadMoreState int state);

    @Override
    public void onViewAttachedToWindow(View v) {
        if (isReady) {
            loadMore();
        }
    }

    /**
     * execute load more
     */
    protected void loadMore() {
        setState(LoadMoreState.LOADING);
        if (mCallback != null) {
            mCallback.onLoadMore();
        }
    }

    /**
     * get load more state
     *
     * @return state
     */
    public int getState() {
        return mState;
    }

    /**
     * set load more state
     *
     * @param state state
     */
    public void setState(@LoadMoreState int state) {
        mState = state;
        isReady = (mState == LoadMoreState.READY);
        onStateChange(mState);
    }

    /**
     * be ready to load more
     */
    public void setReady() {
        isReady = true;
        itemView.removeCallbacks(mRunnable);
        itemView.postDelayed(mRunnable, 500);
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            loadMore();
        }
    };

    @Override
    public void onViewDetachedFromWindow(View v) {
        itemView.removeCallbacks(mRunnable);
    }
}
