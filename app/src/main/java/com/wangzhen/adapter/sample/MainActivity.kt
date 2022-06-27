package com.wangzhen.adapter.sample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wangzhen.adapter.sample.databinding.ActivityMainBinding
import com.wangzhen.adapter.sample.rn.ReactNativeActivity

/**
 * MainActivity
 * Created by wangzhen on 2021/5/26
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHeaderFooter.setOnClickListener {
            startActivity(Intent(this, HeaderFooterActivity::class.java))
        }

        binding.btnLoadMore.setOnClickListener {
            startActivity(Intent(this, LoadMoreActivity::class.java))
        }

        binding.btnReactNative.setOnClickListener {
            startActivity(Intent(this, ReactNativeActivity::class.java))
        }
    }
}