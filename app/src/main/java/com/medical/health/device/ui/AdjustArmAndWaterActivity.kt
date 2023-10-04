package com.medical.health.device.ui

import android.content.Intent
import com.medical.health.device.base.BaseActivity
import com.medical.health.device.databinding.ActivityAdjustArmAndWaterBinding
import com.medical.health.device.databinding.ActivityAdjustWashHeadBinding
import com.medical.health.device.databinding.ActivityPushChairBinding
import com.medical.health.device.kt_extension.click
import com.medical.health.device.kt_extension.clickWithTrigger

class AdjustArmAndWaterActivity:BaseActivity() {
    override val bind by getBind<ActivityAdjustArmAndWaterBinding>()

    override fun initView() {
        super.initView()
        bind.includeTitleview.tvBack.click {finish()}
        bind.includeTitleview.tvTitle.text = "自动洗浴"
        bind.tvConfirmArm.clickWithTrigger {  }
        bind.tvConfirmTemperature.clickWithTrigger{
            startActivity(Intent(this, AutoBathActivity::class.java))
        }
    }
}