package com.dimeno.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.dimeno.adapter.callback.OnLoadMoreCallback;
import com.dimeno.adapter.footer.LoadMoreFooter;
import com.dimeno.adapter.footer.LoadMoreFooterImpl;
import com.dimeno.adapter.meta.LoadMoreState;

import java.util.List;

/**
 * load more recycler adapter
 * Created by wangzhen on 2020/6/9.
 */
public abstract class LoadRecyclerAdapter<T> extends RecyclerAdapter<T> implements OnLoadMoreCallback {
    private static final int VIEW_TYPE_LOAD_MORE = VIEW_TYPE_EMPTY - 1; // -40002
    private LoadMoreFooter mLoadMoreFooter;

    public LoadRecyclerAdapter(List<T> list, ViewGroup parent) {
        super(list);
        setLoadMore((mLoadMoreFooter = createLoadMoreFooter()).onCreateView(parent));
    }

    /**
     * create a load more footer
     *
     * @return LoadMoreFooter
     */
    protected LoadMoreFooter createLoadMoreFooter() {
        return new LoadMoreFooterImpl(this);
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
