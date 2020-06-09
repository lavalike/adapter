package com.dimeno.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dimeno.adapter.callback.OnItemClickCallback;
import com.dimeno.adapter.callback.OnItemLongClickCallback;

import java.util.List;

/**
 * RecyclerAdapter
 * Created by wangzhen on 2020/6/9.
 */
public abstract class RecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<T> mDatas;
    private OnItemClickCallback mItemClickCallback;
    private OnItemLongClickCallback mItemLongClickCallback;

    public RecyclerAdapter(List<T> list) {
        this.mDatas = list;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        setItemEvent(holder);
        if (holder instanceof RecyclerViewHolder) {
            ((RecyclerViewHolder<T>) holder).setData(mDatas.get(position));
        }
    }

    private void setItemEvent(final RecyclerView.ViewHolder holder) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickCallback != null) {
                    mItemClickCallback.onItemClick(v, holder.getLayoutPosition());
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mItemLongClickCallback != null) {
                    mItemLongClickCallback.onItemLongClick(v, holder.getLayoutPosition());
                }
                return false;
            }
        });
    }

    public void setOnClickCallback(OnItemClickCallback callback) {
        this.mItemClickCallback = callback;
    }

    public void setOnLongClickCallback(OnItemLongClickCallback callback) {
        this.mItemLongClickCallback = callback;
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public List<T> getDatas() {
        return mDatas;
    }
}
