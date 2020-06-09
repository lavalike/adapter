package com.dimeno.adapter.sample.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dimeno.adapter.RecyclerAdapter;
import com.dimeno.adapter.sample.entity.UserEntity;
import com.dimeno.adapter.sample.holder.UserViewHolder;

import java.util.List;

/**
 * UserAdapter
 * Created by wangzhen on 2020/6/9.
 */
public class UserAdapter extends RecyclerAdapter<UserEntity> {

    public UserAdapter(List<UserEntity> list) {
        super(list);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(parent);
    }
}
