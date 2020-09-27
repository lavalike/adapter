package com.dimeno.adapter.sample.holder;

import com.dimeno.adapter.base.RecyclerItem;
import com.dimeno.adapter.sample.R;

/**
 * HeaderHolder
 * Created by wangzhen on 2020/6/10.
 */
public class HeaderHolder extends RecyclerItem {
    @Override
    public int layout() {
        return R.layout.item_header_layout;
    }
}
