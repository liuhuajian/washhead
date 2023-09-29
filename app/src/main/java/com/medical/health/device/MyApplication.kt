package com.medical.health.device

import android.support.multidex.MultiDexApplication
import com.clj.fastble.BleManager
import com.tencent.bugly.crashreport.CrashReport

/**
 * @ProjectName:
 * @Description:
 * @Author: liuhuajian
 * @CreateDate： 2023/9/28 15:05
 * @Version: 0.1
 */
class MyApplication:MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        BleManager.getInstance().init(this)
        CrashReport.initCrashReport(applicationContext);
    }
}