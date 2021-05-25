package com.wangzhen.adapter.sample.adapter

import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wangzhen.adapter.LoadRecyclerAdapter
import com.wangzhen.adapter.annotation.LoadMoreState
import com.wangzhen.adapter.footer.LoadMoreFooter
import com.wangzhen.adapter.sample.entity.UserEntity
import com.wangzhen.adapter.sample.holder.UserViewHolder
import java.util.*

/**
 * UserAdapter
 * Created by wangzhen on 2020/6/9.
 */
class UserAdapter(list: List<UserEntity>, parent: ViewGroup?) : LoadRecyclerAdapter<UserEntity>(list, parent) {
    override fun createLoadMoreFooter(): LoadMoreFooter {
        return UserLoadMoreFooter(this)
    }

    override fun onAbsCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder(parent)
    }

    override fun onLoadMore() {
        Handler(Looper.getMainLooper()).postDelayed({
            if (mDatas.size < 15) {
                val list: MutableList<UserEntity> = ArrayList()
                for (i in 0..0) {
                    list.add(UserEntity("李四", mDatas.size + 1))
                }
                addData(list)
            } else {
                setState(LoadMoreState.NO_MORE)
            }
        }, 100)
    }
}