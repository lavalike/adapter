package com.dimeno.adapter.footer;

import android.view.View;

import com.dimeno.adapter.R;
import com.dimeno.adapter.annotation.LoadMoreState;
import com.dimeno.adapter.callback.OnLoadMoreCallback;

/**
 * LoadMoreFooterImpl
 * Created by wangzhen on 2020/6/10.
 */
public final class LoadMoreFooterImpl extends LoadMoreFooter implements View.OnClickListener {
    private View mContainerLoading;
    private View mContainerNoMore;
    private View mContainerError;

    public LoadMoreFooterImpl(OnLoadMoreCallback callback) {
        super(callback);
    }

    @Override
    public int layout() {
        return R.layout.item_load_more_layout;
    }

    @Override
    public void onFooterViewCreated(View itemView) {
        mContainerLoading = itemView.findViewById(R.id.container_loading);
        mContainerNoMore = itemView.findViewById(R.id.container_no_more);
        mContainerError = itemView.findViewById(R.id.container_error);
        mContainerError.setOnClickListener(this);
    }

    @Override
    public void onStateChange(int state) {
        mContainerLoading.setVisibility(state == LoadMoreState.LOADING ? View.VISIBLE : View.GONE);
        mContainerNoMore.setVisibility(state == LoadMoreState.NO_MORE ? View.VISIBLE : View.GONE);
        mContainerError.setVisibility(state == LoadMoreState.ERROR ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.container_error) {
            loadMore();
        }
    }
}
