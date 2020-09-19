package com.dimeno.adapter.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * recycler item
 * Created by wangzhen on 2020/6/10.
 */
public abstract class RecyclerItem {
    /**
     * init and return view
     *
     * @param parent parent
     * @return view
     */
    public View onCreateView(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(layout(), parent, false);
        onViewCreated(itemView);
        return itemView;
    }

    /**
     * set layout res
     *
     * @return res id
     */
    protected abstract int layout();

    /**
     * handle views here
     *
     * @param itemView item view
     */
    protected void onViewCreated(View itemView) {

    }
}
