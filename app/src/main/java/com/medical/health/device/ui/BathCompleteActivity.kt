package com.medical.health.device.ui

import android.content.Intent
import com.medical.health.device.base.BaseActivity
import com.medical.health.device.databinding.ActivityAdjustArmAndWaterBinding
import com.medical.health.device.databinding.ActivityAdjustWashHeadBinding
import com.medical.health.device.databinding.ActivityAutoBathBinding
import com.medical.health.device.databinding.ActivityBathCompleteBinding
import com.medical.health.device.databinding.ActivityPushChairBinding
import com.medical.health.device.kt_extension.click
import com.medical.health.device.kt_extension.clickWithTrigger

class BathCompleteActivity:BaseActivity() {
    override val bind by getBind<ActivityBathCompleteBinding>()

    override fun initView() {
        super.initView()
        bind.tvConfirm.clickWithTrigger { startActivity(Intent(this, MainActivity::class.java)) }
    }
}