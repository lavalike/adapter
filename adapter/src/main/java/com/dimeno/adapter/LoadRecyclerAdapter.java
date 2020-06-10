package com.dimeno.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.dimeno.adapter.callback.OnLoadMoreCallback;
import com.dimeno.adapter.footer.LoadMoreFooter;
import com.dimeno.adapter.meta.LoadMoreState;

import java.util.List;

/**
 * load more recycler adapter
 * Created by wangzhen on 2020/6/9.
 */
public abstract class LoadRecyclerAdapter<T> extends RecyclerAdapter<T> implements OnLoadMoreCallback {
    private static final int VIEW_TYPE_LOAD_MORE = VIEW_TYPE_EMPTY - 1; // -40002
    private final LoadMoreFooter mLoadMoreFooter;

    public LoadRecyclerAdapter(List<T> list, ViewGroup parent) {
        super(list);
        mLoadMoreFooter = new LoadMoreFooter(this);
        setLoadMore(mLoadMoreFooter.onCreateView(parent));
    }

    /**
     * set load more view
     *
     * @param itemView itemView
     */
    private void setLoadMore(View itemView) {
        mFooters.put(VIEW_TYPE_LOAD_MORE, itemView);
    }

    /**
     * set load more state
     *
     * @param state state
     * @see LoadMoreState
     */
    public void setState(@LoadMoreState int state) {
        mLoadMoreFooter.setState(state);
    }
}
