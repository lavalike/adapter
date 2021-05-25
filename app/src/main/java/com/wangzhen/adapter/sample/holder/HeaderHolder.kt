package com.wangzhen.adapter.sample.holder

import com.wangzhen.adapter.base.RecyclerItem
import com.wangzhen.adapter.sample.R

/**
 * HeaderHolder
 * Created by wangzhen on 2021/5/25
 */
class HeaderHolder : RecyclerItem() {
    public override fun layout(): Int {
        return R.layout.item_header_layout
    }
}