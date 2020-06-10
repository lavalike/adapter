package com.dimeno.adapter.sample.holder;

import android.view.View;
import android.widget.Toast;

import com.dimeno.adapter.base.RecyclerItem;
import com.dimeno.adapter.sample.R;

/**
 * EmptyHolder
 * Created by wangzhen on 2020/6/10.
 */
public class EmptyHolder extends RecyclerItem {
    @Override
    public int layout() {
        return R.layout.item_empty_layout;
    }

    @Override
    public void onViewCreated(View itemView) {
        itemView.findViewById(R.id.btn_retry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "刷新", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
