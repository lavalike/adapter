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
import com.wangzhen.adapter.sample.adapter.UserAdapter
import com.wangzhen.adapter.sample.databinding.ActivityLoadMoreBinding
import com.wangzhen.adapter.sample.entity.UserEntity
import com.wangzhen.adapter.sample.holder.EmptyHolder
import com.wangzhen.adapter.sample.holder.HeaderHolder
import java.util.*

/**
 * LoadMoreActivity
 * Created by wangzhen on 2021/5/26
 */
class LoadMoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoadMoreBinding
    private lateinit var recycler: RecyclerView
    private var adapter: UserAdapter? = null
    private var paint: Paint? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityLoadMoreBinding.inflate(layoutInflater).apply {
            binding = this
        }.root)
        title = "Load More"
        initViews()
        bind()
    }

    private fun initViews() {
        recycler = binding.recycler
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

        binding.btnClear.setOnClickListener {
            adapter?.let {
                it.setData(null)
                it.notifyDataSetChanged()
            }
        }
    }

    fun bind() {
        val list: MutableList<UserEntity> = ArrayList()
        for (i in 1..10) {
            list.add(UserEntity("张三", i))
        }
        adapter = UserAdapter(list, recycler).apply {
            setOnClickCallback { _, position ->
                datas.removeAt(position)
                notifyItemRemoved(position + headersCount)
            }
            setEmpty(EmptyHolder().onCreateView(recycler))
            addHeader(HeaderHolder().onCreateView(recycler))
        }
        recycler.adapter = adapter
    }
}