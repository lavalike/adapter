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
    protected OnLoadMoreCallback mCallback;
    protected int mState = LoadMoreState.LOADING;

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
     * update load more state
     *
     * @param state state
     */
    public abstract void setState(@LoadMoreState int state);

    @Override
    public void onViewAttachedToWindow(View v) {
        if (mState == LoadMoreState.LOADING) {
            loadMore();
        }
    }

    protected void loadMore() {
        setState(LoadMoreState.LOADING);
        if (mCallback != null) {
            mCallback.onLoadMore();
        }
    }

    @Override
    public void onViewDetachedFromWindow(View v) {

    }
}
