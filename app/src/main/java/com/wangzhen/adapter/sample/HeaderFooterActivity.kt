package com.wangzhen.adapter.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wangzhen.adapter.base.RecyclerItem
import com.wangzhen.adapter.sample.adapter.HeaderFooterAdapter
import com.wangzhen.adapter.sample.databinding.ActivityHeaderFooterBinding
import com.wangzhen.adapter.sample.entity.UserEntity
import com.wangzhen.adapter.sample.holder.FooterHolder
import com.wangzhen.adapter.sample.holder.HeaderHolder

/**
 * HeaderFooterActivity
 * Created by wangzhen on 2021/5/26
 */
class HeaderFooterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHeaderFooterBinding
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityHeaderFooterBinding.inflate(layoutInflater).apply {
            binding = this
        }.root)

        title = "Header Footer"

        recycler = binding.recycler
        recycler.layoutManager = LinearLayoutManager(this)

        bind()
    }

    private fun bind() {
        val list: MutableList<UserEntity> = mutableListOf()
        for (i in 1..10) {
            list.add(UserEntity("张三", i))
        }
        val adapter = HeaderFooterAdapter(list).apply {
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