package com.wangzhen.adapter.sample;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wangzhen.adapter.base.RecyclerItem;
import com.wangzhen.adapter.callback.OnItemClickCallback;
import com.wangzhen.adapter.sample.adapter.UserAdapter;
import com.wangzhen.adapter.sample.entity.UserEntity;
import com.wangzhen.adapter.sample.holder.EmptyHolder;
import com.wangzhen.adapter.sample.holder.FooterHolder;
import com.wangzhen.adapter.sample.holder.HeaderHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private UserAdapter mAdapter;
    private Paint mPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycler = findViewById(R.id.recycler);

        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#33888888"));
        mRecycler.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.onDraw(c, parent, state);
                int childCount = parent.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = parent.getChildAt(i);
                    if (childAt != null && i < childCount - 1) {
                        int left = childAt.getLeft();
                        int right = childAt.getRight();
                        int top = childAt.getBottom();
                        int bottom = top + 1;
                        c.drawRect(new Rect(left, top, right, bottom), mPaint);
                    }
                }
            }

            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int position = parent.getChildAdapterPosition(view);
                if (position == parent.getAdapter().getItemCount() - 1) {
                    outRect.set(0, 0, 0, 0);
                } else {
                    outRect.set(0, 0, 0, 1);
                }
            }
        });
        init();
    }

    public void init() {
        List<UserEntity> list = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            list.add(new UserEntity("张三", i + 1));
        }
        mAdapter = new UserAdapter(list, mRecycler);
        mAdapter.setOnClickCallback(new OnItemClickCallback() {
            @Override
            public void onItemClick(View itemView, int position) {
                mAdapter.getDatas().remove(position);
                mAdapter.notifyItemRemoved(position + mAdapter.getHeadersCount());
            }
        });
        mAdapter.setEmpty(new EmptyHolder().onCreateView(mRecycler));

        mAdapter.addHeader(new HeaderHolder().onCreateView(mRecycler));
        mAdapter.addHeader(new FooterHolder().onCreateView(mRecycler));
        mAdapter.addHeader(new HeaderHolder().onCreateView(mRecycler));

        mAdapter.addFooter(new RecyclerItem() {
            @Override
            protected int layout() {
                return R.layout.item_footer_red_layout;
            }
        }.onCreateView(mRecycler));
        mAdapter.addFooter(new RecyclerItem() {
            @Override
            protected int layout() {
                return R.layout.item_footer_green_layout;
            }
        }.onCreateView(mRecycler));
        mAdapter.addFooter(new FooterHolder().onCreateView(mRecycler));

        mRecycler.setAdapter(mAdapter);
    }
}