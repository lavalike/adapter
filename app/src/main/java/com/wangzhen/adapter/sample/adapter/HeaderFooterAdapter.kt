package com.wangzhen.adapter.sample.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wangzhen.adapter.RecyclerAdapter
import com.wangzhen.adapter.sample.entity.UserEntity
import com.wangzhen.adapter.sample.holder.UserViewHolder

/**
 * HeaderFooterAdapter
 * Created by wangzhen on 2021/5/26
 */
class HeaderFooterAdapter(list: List<UserEntity>?) : RecyclerAdapter<UserEntity>(list) {
    override fun onAbsCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder(parent);
    }
}