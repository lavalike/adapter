package com.dimeno.adapter.footer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimeno.adapter.callback.OnLoadMoreCallback;
import com.dimeno.adapter.meta.LoadMoreState;

/**
 * load more parent footer
 * Created by wangzhen on 2020/6/10.
 */
public abstract class LoadMoreFooter implements View.OnAttachStateChangeListener {
    private OnLoadMoreCallback mCallback;
    private int mState = LoadMoreState.READY;
    private boolean isReady = true;

    public LoadMoreFooter(OnLoadMoreCallback callback) {
        this.mCallback = callback;
    }

    /**
     * init and return footer view
     *
     * @param parent parent
     * @return view
     */
    public View onCreateView(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(layout(), parent, false);
        itemView.addOnAttachStateChangeListener(this);
        onViewCreated(itemView);
        return itemView;
    }

    /**
     * set footer layout res
     *
     * @return res id
     */
    public abstract int layout();

    /**
     * handle views here
     *
     * @param itemView item view
     */
    public abstract void onViewCreated(View itemView);

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
    }

    @Override
    public void onViewDetachedFromWindow(View v) {

    }
}
