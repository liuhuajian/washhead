package com.medical.health.device.ui

import android.Manifest
import android.content.Intent
import android.util.DisplayMetrics
import com.clj.fastble.BleManager
import com.medical.health.device.R
import com.medical.health.device.base.BaseActivity
import com.medical.health.device.databinding.ActivityMainBinding
import com.medical.health.device.kt_extension.clickWithTrigger
import com.medical.health.device.service.BleManagerUtils
import com.medical.health.device.utils.MyLogger
import com.medical.health.device.vm.BleVm
import com.xpermissions.xcynice.PermissionCallback
import com.xpermissions.xcynice.XPermission
import java.security.Permission
import java.security.Permissions

class MainActivity : BaseActivity() {

    override val bind by getBind<ActivityMainBinding>()

    private val permisss = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION)

    override fun initView() {
        super.initView()
        bleVm.sendFunction()
        XPermission.request(this, *permisss){
            allGranted,deniedList ->
            run {
                if (allGranted) {
                    MyLogger.e("授权通过")
                    if (BleManagerUtils.judgeConnectedBle()){
                        bind.tvBleStatus.run {
                            text ="蓝牙已连接"
                            setBackgroundDrawable(resources.getDrawable(R.drawable.bg_green_gradual))
                        }
                    }else{
                        bind.tvBleStatus.run {
                            text ="蓝牙未连接"
                            setBackgroundDrawable(resources.getDrawable(R.drawable.bg_grey_gradual))
                        }
                    }
                } else {
                    MyLogger.e("有权限未通过-->$deniedList")
                }
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        if (BleManagerUtils.judgeConnectedBle()){
            bind.tvBleStatus.run {
                text ="蓝牙已连接"
                setBackgroundDrawable(resources.getDrawable(R.drawable.bg_green_gradual))
            }
        }else{
            bind.tvBleStatus.run {
                text ="蓝牙未连接"
                setBackgroundDrawable(resources.getDrawable(R.drawable.bg_grey_gradual))
            }
        }
    }

    override fun initListener() {
        bind.tvBleStatus.clickWithTrigger { startActivity(Intent(this, BleListActivity::class.java)) }
        bind.tvStart.clickWithTrigger { startActivity(Intent(this, CheckChairActivity::class.java)) }
        bind.tvTestEntry.clickWithTrigger{ startActivity(Intent(this, TestHomeActivity::class.java)) }
        bleVm.sendSuccess.observe(this){
            window.decorView.run {
                MyLogger.e("height:$height-->width-->$width")
            }
//            val displayMetrics = DisplayMetrics()
//            display.getMetrics(displayMetrics)
//            MyLogger.e("densityDpi: ${displayMetrics.densityDpi}")
//            MyLogger.e("density: ${displayMetrics.density}")
//            val displayMetrics = applicationContext.resources.displayMetrics
//            MyLogger.e("densityDpi: " + displayMetrics.densityDpi)
//            MyLogger.e("density: " + displayMetrics.density)
//            MyLogger.e("widthPixels: " + displayMetrics.widthPixels)
//            MyLogger.e("heightPixels: " + displayMetrics.heightPixels)
        }
    }

    val bleVm = BleVm()

}