package com.medical.health.device

import android.view.View
import com.clj.fastble.BleManager
import com.clj.fastble.callback.BleScanCallback
import com.clj.fastble.data.BleDevice
import com.clj.fastble.scan.BleScanRuleConfig
import com.medical.health.device.base.BaseActivity
import com.medical.health.device.databinding.ActivityMainBinding
import com.medical.health.device.kt_extension.clickWithTrigger
import com.medical.health.device.utils.MyLogger
import com.medical.health.device.vm.BleVm
import com.tencent.bugly.crashreport.CrashReport

class MainActivity : BaseActivity() {

    override val bind by getBind<ActivityMainBinding>()

    override fun initView() {
        super.initView()
        bind.btnClick.clickWithTrigger(1000) {
            bleVm.sendFunction()
//            BleManager.getInstance().initScanRule(
//                BleScanRuleConfig.Builder()
//                    .setScanTimeOut(10000)
//                    .build()
//            )
//            BleManager.getInstance().scan(object :BleScanCallback(){
//                override fun onScanStarted(success: Boolean) {
//
//                }
//
//                override fun onScanning(bleDevice: BleDevice?) {
//                    MyLogger.e("onScanning-->${bleDevice?.name}")
//                }
//
//                override fun onScanFinished(scanResultList: MutableList<BleDevice>?) {
//
//                }
//            })
        }
    }

    override fun initListener() {

        bleVm.sendSuccess.observe(this){
            MyLogger.e("bleVm-->$it")

            val displayMetrics = applicationContext.resources.displayMetrics
            MyLogger.e("densityDpi: " + displayMetrics.densityDpi)
            MyLogger.e("density: " + displayMetrics.density)
            MyLogger.e("widthPixels: " + displayMetrics.widthPixels)
            MyLogger.e("heightPixels: " + displayMetrics.heightPixels)
        }
    }

    val bleVm = BleVm()

}