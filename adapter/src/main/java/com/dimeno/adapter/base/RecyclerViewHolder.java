package com.dimeno.adapter.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * recycler view holder
 * Created by wangzhen on 2020/6/9.
 */
public abstract class RecyclerViewHolder<T> extends RecyclerView.ViewHolder {
    protected T mData;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    /**
     * set data and call bind()
     *
     * @param data data
     */
    public void setData(T data) {
        this.mData = data;
        bind();
    }

    /**
     * bind data¬
     */
    public abstract void bind();
}
