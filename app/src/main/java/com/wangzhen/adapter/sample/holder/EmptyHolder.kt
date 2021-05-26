package com.wangzhen.adapter.sample.holder

import android.view.View
import com.wangzhen.adapter.base.RecyclerItem
import com.wangzhen.adapter.sample.LoadMoreActivity
import com.wangzhen.adapter.sample.R

/**
 * EmptyHolder
 * Created by wangzhen on 2021/5/25
 */
class EmptyHolder : RecyclerItem() {
    public override fun layout(): Int {
        return R.layout.item_empty_layout
    }

    override fun onViewCreated(itemView: View) {
        itemView.findViewById<View>(R.id.btn_retry).setOnClickListener { v ->
            if (v.context is LoadMoreActivity) {
                (v.context as LoadMoreActivity).bind()
            }
        }
    }
}