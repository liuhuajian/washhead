package com.medical.health.device.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.medical.health.device.kt_extension.withTrigger
import com.medical.health.device.utils.ActivityManager

/**
 * @ProjectName:
 * @Description:
 * @Author: liuhuajian
 * @CreateDate： 2023/9/28 15:17
 * @Version: 0.1
 */
abstract class BaseActivity:AppCompatActivity(),IBaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 无title
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        //全屏
        window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        bind?.root?.apply {
            setContentView(this)
        }
        ActivityManager.getInstance().addActivity(this)
        initView()
        initListener()
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.getInstance().removeActivity(this)
    }

    abstract val bind:ViewBinding?
    protected inline fun <reified T> getBind():Lazy<T> where T: ViewBinding {
        return lazy {
            val clazz = T::class.java
            val method = clazz.getMethod("inflate", LayoutInflater::class.java)
            method.invoke(null, layoutInflater) as T
        }
    }

    open fun initListener(){}
    open fun initView(){}
    open fun initData(){}
}