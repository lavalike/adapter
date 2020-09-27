package com.dimeno.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.dimeno.adapter.annotation.LoadMoreState;
import com.dimeno.adapter.callback.OnLoadMoreCallback;
import com.dimeno.adapter.footer.LoadMoreFooter;
import com.dimeno.adapter.footer.LoadMoreFooterImpl;

import java.util.List;

/**
 * load more recycler adapter
 * Created by wangzhen on 2020/6/9.
 */
public abstract class LoadRecyclerAdapter<T> extends RecyclerAdapter<T> implements OnLoadMoreCallback {
    private static final int VIEW_TYPE_LOAD_MORE = VIEW_TYPE_EMPTY - 1; // -40002
    private ViewGroup parent;
    private LoadMoreFooter mLoadMoreFooter;

    public LoadRecyclerAdapter(List<T> list, ViewGroup parent) {
        super(list);
        this.parent = parent;
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

    @Override
    public int getItemCount() {
        int count = getDatas() == null ? 0 : getDatas().size();
        if (count == 0) {
            // if data is empty, remove load more footer
            if (mFooters.get(VIEW_TYPE_LOAD_MORE) != null) {
                mFooters.remove(VIEW_TYPE_LOAD_MORE);
            }
        } else {
            // if data is not empty and the load more footer was removed, re-add load more footer here
            if (mFooters.get(VIEW_TYPE_LOAD_MORE) == null && mLoadMoreFooter != null) {
                mFooters.put(VIEW_TYPE_LOAD_MORE, mLoadMoreFooter.onCreateView(parent));
            }
        }
        return super.getItemCount();
    }

    @Override
    public void setData(List<T> list) {
        super.setData(list);
        mLoadMoreFooter.setReady();
    }

    @Override
    public void addData(List<T> list) {
        super.addData(list);
        mLoadMoreFooter.setReady();
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
