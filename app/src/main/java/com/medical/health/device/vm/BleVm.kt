package com.medical.health.device.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.medical.health.device.utils.MyLogger

/**
 * copyright (C),2021-2022, 国民集团健康科技有限公司
 * @ProjectName:
 * @Description:
 * @Author: liuhuajian
 * @CreateDate： 2023/9/28 15:07
 * @Version: 0.1
 */
class BleVm:ViewModel() {
    val sendSuccess = MutableLiveData<String>()

    fun sendFunction(){
        Thread{
            MyLogger.e("sendFunction begin")
            Thread.sleep(1000)
            sendSuccess.postValue("success")
        }.start()
    }
}