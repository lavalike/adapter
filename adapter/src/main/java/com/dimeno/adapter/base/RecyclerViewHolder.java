package com.dimeno.adapter.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * recycler view holder
 * Created by wangzhen on 2020/6/9.
 */
public abstract class RecyclerViewHolder<T> extends RecyclerView.ViewHolder {
    protected T mData;

    public RecyclerViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutId) {
        this(inflate(parent, layoutId, false));
    }

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

    protected <VIEW extends View> VIEW findViewById(@IdRes int id) {
        return itemView.findViewById(id);
    }

    protected static View inflate(ViewGroup parent, @LayoutRes int layoutId, boolean attachToRoot) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, attachToRoot);
    }
}
