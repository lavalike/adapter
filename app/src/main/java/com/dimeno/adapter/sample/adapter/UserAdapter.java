package com.dimeno.adapter.sample.adapter;

import android.os.Handler;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.dimeno.adapter.LoadRecyclerAdapter;
import com.dimeno.adapter.sample.entity.UserEntity;
import com.dimeno.adapter.sample.holder.UserViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * UserAdapter
 * Created by wangzhen on 2020/6/9.
 */
public class UserAdapter extends LoadRecyclerAdapter<UserEntity> {

    public UserAdapter(List<UserEntity> list, ViewGroup parent) {
        super(list, parent);
    }

    @Override
    public RecyclerView.ViewHolder onAbsCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserViewHolder(parent);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<UserEntity> list = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    list.add(new UserEntity("李四", i + 1));
                }
                addData(list);
            }
        }, 500);
    }
}
