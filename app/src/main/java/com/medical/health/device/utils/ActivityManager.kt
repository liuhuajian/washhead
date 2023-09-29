package com.medical.health.device.utils

import android.util.Log
import com.medical.health.device.base.BaseActivity
import java.lang.ref.WeakReference
import java.util.*
import kotlin.system.exitProcess

/**
 * copyright (C),2021-2022, 国民集团健康科技有限公司
 * @ProjectName: 应用Activity、fragment管理类
 * @Description:
 * @Author: liuhuajian
 * @CreateDate： 2021/12/17 19:50
 * @Version: 0.1
 */
class ActivityManager private constructor() {

    companion object {

        @Volatile
        private var activityStack = Stack<WeakReference<BaseActivity>>()

        /**
         * 获得单例对象
         *
         * @return ActivityFragmentManager
         */
        fun getInstance(): ActivityManager {
            return ActivityFragmentManagerHolder.mInstance
        }
    }

    private object ActivityFragmentManagerHolder {
        val mInstance: ActivityManager = ActivityManager()
    }

    /**
     * 获取当前的Activity
     */
    val currentActivity: BaseActivity?
        get() = if (activityStack.size > 0) activityStack.lastElement().get() else null

    /**
     * 添加 Activity
     *
     * @param activity Activity
     */
    fun addActivity(activity: BaseActivity) {
        activityStack.add(WeakReference(activity))
    }

    /**
     * 移除 Activity
     *
     * @param activity Activity
     */
    fun removeActivity(activity: BaseActivity?) {
        if (activity == null) {
            return
        }
        for (activityRef in activityStack) {
            if (activityRef.get() == activity) {
                activityStack.remove(activityRef)
                break
            }
        }
    }

    /**
     * 结束指定Activity
     *
     * @param activity Activity
     */
    fun finishActivity(activity: BaseActivity?) {
        if (activity == null) {
            return
        }
        for (activityRef in activityStack) {
            if (activityRef.get() == activity) {
                activityStack.remove(activityRef)
                break
            }
        }
        activity.finish()
    }

    /**
     * 结束指定Activity
     *
     * @param cls Activity.class
     */
    fun finishActivity(cls: Class<*>?) {
        if (cls == null) {
            return
        }
        for (activityRef in activityStack) {
            if (activityRef.get()?.javaClass == cls) {
                activityStack.remove(activityRef)
                finishActivity(activityRef.get())
                break
            }
        }
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        while (!activityStack.isEmpty()) {
            activityStack.pop().get()?.finish()
        }
        activityStack.clear()
    }

    /**
     * 退出应用程序
     */
    fun appExit() {
        try {
            finishAllActivity()
            android.os.Process.killProcess(android.os.Process.myPid())
            exitProcess(0)
        } catch (e: Exception) {
            Log.e("ActivityFragmentManager", "appExit exception: " + e.message)
        }
    }
}
