package com.wangzhen.adapter.sample

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wangzhen.adapter.base.RecyclerItem
import com.wangzhen.adapter.sample.adapter.UserAdapter
import com.wangzhen.adapter.sample.entity.UserEntity
import com.wangzhen.adapter.sample.holder.EmptyHolder
import com.wangzhen.adapter.sample.holder.FooterHolder
import com.wangzhen.adapter.sample.holder.HeaderHolder
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var recycler: RecyclerView
    private var adapter: UserAdapter? = null
    private var paint: Paint? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler = findViewById(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.parseColor("#33888888")
        }
        recycler.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                super.onDraw(c, parent, state)
                val childCount = parent.childCount
                for (i in 0 until childCount) {
                    val childAt = parent.getChildAt(i)
                    if (childAt != null && i < childCount - 1) {
                        val left = childAt.left
                        val right = childAt.right
                        val top = childAt.bottom
                        val bottom = top + 1
                        c.drawRect(Rect(left, top, right, bottom), paint!!)
                    }
                }
            }

            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                super.getItemOffsets(outRect, view, parent, state)
                val position = parent.getChildAdapterPosition(view)
                if (position == parent.adapter!!.itemCount - 1) {
                    outRect[0, 0, 0] = 0
                } else {
                    outRect[0, 0, 0] = 1
                }
            }
        })
        init()
    }

    fun init() {
        val list: MutableList<UserEntity> = ArrayList()
        for (i in 0..0) {
            list.add(UserEntity("张三", i + 1))
        }
        adapter = UserAdapter(list, recycler).apply {
            setOnClickCallback { _, position ->
                datas.removeAt(position)
                notifyItemRemoved(position + headersCount)
            }
            setEmpty(EmptyHolder().onCreateView(recycler))

            addHeader(HeaderHolder().onCreateView(recycler))
            addHeader(FooterHolder().onCreateView(recycler))
            addHeader(HeaderHolder().onCreateView(recycler))

            addFooter(object : RecyclerItem() {
                override fun layout() = R.layout.item_footer_red_layout
            }.onCreateView(recycler))

            addFooter(object : RecyclerItem() {
                override fun layout() = R.layout.item_footer_green_layout
            }.onCreateView(recycler))

            addFooter(FooterHolder().onCreateView(recycler))
        }
        recycler.adapter = adapter
    }
}