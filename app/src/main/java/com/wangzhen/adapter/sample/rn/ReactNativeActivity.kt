package com.wangzhen.adapter.sample.rn

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.react.PackageList
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactRootView
import com.facebook.react.common.LifecycleState
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import com.facebook.soloader.SoLoader
import com.wangzhen.adapter.sample.BuildConfig

/**
 * ReactNativeActivity
 * Created by laval on 2022/6/27.
 */
class ReactNativeActivity : AppCompatActivity(), DefaultHardwareBackBtnHandler {
    private lateinit var rootView: ReactRootView
    private lateinit var instanceManager: ReactInstanceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SoLoader.init(this, false)

        rootView = ReactRootView(this)

        val packages = PackageList(application).packages

        // 有一些第三方可能不能自动链接，对于这些包我们可以用下面的方式手动添加进来：
        // packages.add(new MyReactNativePackage());
        // 同时需要手动把他们添加到`settings.gradle`和 `app/build.gradle`配置文件中。

        instanceManager = ReactInstanceManager.builder()
            .setApplication(application)
            .setCurrentActivity(this)
            .setBundleAssetName("index.android.bundle")
            .setJSMainModulePath("index")
            .addPackages(packages)
            .setUseDeveloperSupport(BuildConfig.DEBUG)
            .setInitialLifecycleState(LifecycleState.RESUMED)
            .build()

        // 注意这里的GameDetail 必须对应"index.js"中的"AppRegistry.registerComponent()"的第一个参数
        rootView.startReactApplication(instanceManager, "ReactNative", null)
        setContentView(rootView)
    }

    override fun invokeDefaultOnBackPressed() {
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        instanceManager.onHostPause(this)
    }

    override fun onResume() {
        super.onResume()
        instanceManager.onHostResume(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        instanceManager.onHostDestroy(this)
        rootView.unmountReactApplication()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        instanceManager.onBackPressed()
    }

}