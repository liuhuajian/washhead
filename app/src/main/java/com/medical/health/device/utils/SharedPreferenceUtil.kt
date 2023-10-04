package com.medical.health.device.utils

import android.content.Context
import android.content.SharedPreferences
import com.medical.health.device.MyApplication

/**
 *
 * @Creator:liuhuajian
 * @创建日期： 2023/9/30 13:56Ø
 * @版本0.2
 * @类说明：
 */
object SharedPreferenceUtil{
    val MAC_ADDRESS = "device_mac_address"
    private val sp: SharedPreferences = MyApplication.mContext.getSharedPreferences("data", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sp.edit()

    fun saveSpCheckMode(isBootWizard: Int) {
        editor.putInt("MODE_CHECK", isBootWizard).commit()
    }

    val spCheckMode: Int
        get() = sp.getInt("MODE_CHECK", 1)

    fun saveSpString(key: String?, value: String?): Boolean {
        return editor.putString(key, value).commit()
    }

    fun getSpString(key: String?): String? {
        return sp.getString(key, "")
    }
}