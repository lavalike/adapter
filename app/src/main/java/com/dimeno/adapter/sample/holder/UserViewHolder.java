package com.dimeno.adapter.sample.holder;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dimeno.adapter.RecyclerViewHolder;
import com.dimeno.adapter.sample.R;
import com.dimeno.adapter.sample.entity.UserEntity;

/**
 * UserViewHolder
 * Created by wangzhen on 2020/6/9.
 */
public class UserViewHolder extends RecyclerViewHolder<UserEntity> {

    private TextView mTvName;
    private TextView mTvAge;

    public UserViewHolder(@NonNull ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_layout, parent, false));
        mTvName = itemView.findViewById(R.id.tv_name);
        mTvAge = itemView.findViewById(R.id.tv_age);
    }

    @Override
    public void bind() {
        mTvName.setText(mData.name);
        mTvAge.setText(String.valueOf(mData.age));
    }
}
