package com.wangzhen.adapter.sample.adapter

import android.view.View
import com.wangzhen.adapter.annotation.LoadMoreState
import com.wangzhen.adapter.callback.OnLoadMoreCallback
import com.wangzhen.adapter.footer.LoadMoreFooter
import com.wangzhen.adapter.sample.R


/**
 * UserLoadMoreFooter
 * Created by wangzhen on 2021/5/25
 */
class UserLoadMoreFooter(callback: OnLoadMoreCallback) : LoadMoreFooter(callback) {
    private lateinit var containerLoading: View
    private lateinit var containerNoMore: View
    private lateinit var containerError: View

    public override fun layout(): Int {
        return R.layout.user_load_more_layout
    }

    override fun onFooterViewCreated(itemView: View) {
        containerLoading = itemView.findViewById(R.id.container_loading)
        containerNoMore = itemView.findViewById(R.id.container_no_more)
        containerError = itemView.findViewById(R.id.container_error)
        containerError.setOnClickListener {
            loadMore()
        }
    }

    override fun onStateChange(state: Int) {
        containerLoading.visibility = if (state == LoadMoreState.LOADING) View.VISIBLE else View.GONE
        containerNoMore.visibility = if (state == LoadMoreState.NO_MORE) View.VISIBLE else View.GONE
        containerError.visibility = if (state == LoadMoreState.ERROR) View.VISIBLE else View.GONE
    }
}