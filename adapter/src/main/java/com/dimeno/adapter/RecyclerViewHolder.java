package com.dimeno.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * BaseRecyclerViewHolder
 * Created by wangzhen on 2020/6/9.
 */
public abstract class RecyclerViewHolder<T> extends RecyclerView.ViewHolder {
    protected T mData;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void setData(T data) {
        this.mData = data;
        bind();
    }

    public abstract void bind();
}
