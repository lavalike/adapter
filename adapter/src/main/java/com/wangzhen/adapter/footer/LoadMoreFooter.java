package com.wangzhen.adapter.footer;

import android.view.View;

import com.wangzhen.adapter.annotation.LoadMoreState;
import com.wangzhen.adapter.base.RecyclerItem;
import com.wangzhen.adapter.callback.OnLoadMoreCallback;

/**
 * load more parent footer
 * Created by wangzhen on 2020/6/10.
 */
public abstract class LoadMoreFooter extends RecyclerItem implements View.OnAttachStateChangeListener {
    private final OnLoadMoreCallback callback;
    private int state = LoadMoreState.READY;
    private View itemView;

    public LoadMoreFooter(OnLoadMoreCallback callback) {
        this.callback = callback;
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
        if (state == LoadMoreState.READY) {
            loadMore();
        }
    }

    /**
     * execute load more
     */
    protected void loadMore() {
        setState(LoadMoreState.LOADING);
        if (callback != null) {
            callback.onLoadMore();
        }
    }

    /**
     * get load more state
     *
     * @return state
     */
    public int getState() {
        return state;
    }

    /**
     * set load more state
     *
     * @param state state
     */
    public void setState(@LoadMoreState int state) {
        this.state = state;
        onStateChange(this.state);
    }

    /**
     * be ready to load more
     */
    public void setReady() {
        state = LoadMoreState.READY;
        itemView.removeCallbacks(mRunnable);
        itemView.postDelayed(mRunnable, 500);
    }

    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (state == LoadMoreState.READY) {
                loadMore();
            }
        }
    };

    @Override
    public void onViewDetachedFromWindow(View v) {
        itemView.removeCallbacks(mRunnable);
    }
}
