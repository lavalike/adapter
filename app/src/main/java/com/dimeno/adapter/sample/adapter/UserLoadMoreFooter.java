package com.dimeno.adapter.sample.adapter;

import android.view.View;

import com.dimeno.adapter.annotation.LoadMoreState;
import com.dimeno.adapter.callback.OnLoadMoreCallback;
import com.dimeno.adapter.footer.LoadMoreFooter;
import com.dimeno.adapter.sample.R;

/**
 * UserLoadMoreFooter
 * Created by wangzhen on 2020/6/10.
 */
public class UserLoadMoreFooter extends LoadMoreFooter implements View.OnClickListener {

    private View mContainerLoading;
    private View mContainerNoMore;
    private View mContainerError;

    public UserLoadMoreFooter(OnLoadMoreCallback callback) {
        super(callback);
    }

    @Override
    public int layout() {
        return R.layout.user_load_more_layout;
    }

    @Override
    public void onFooterViewCreated(View itemView) {
        mContainerLoading = itemView.findViewById(com.dimeno.adapter.R.id.container_loading);
        mContainerNoMore = itemView.findViewById(com.dimeno.adapter.R.id.container_no_more);
        mContainerError = itemView.findViewById(com.dimeno.adapter.R.id.container_error);
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
        if (v.getId() == com.dimeno.adapter.R.id.container_error) {
            loadMore();
        }
    }
}
