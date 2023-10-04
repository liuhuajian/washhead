package com.medical.health.device.ui

import android.content.Intent
import com.medical.health.device.base.BaseActivity
import com.medical.health.device.databinding.ActivityAdjustWashHeadBinding
import com.medical.health.device.databinding.ActivityPushChairBinding
import com.medical.health.device.kt_extension.click
import com.medical.health.device.kt_extension.clickWithTrigger

class AdjustWashHeadActivity:BaseActivity() {
    override val bind by getBind<ActivityAdjustWashHeadBinding>()

    override fun initView() {
        super.initView()
        bind.includeTitleview.tvBack.click {finish()}
        bind.includeTitleview.tvTitle.text = "调节洗头机"
        bind.tvConfirm.clickWithTrigger { startActivity(Intent(this, CheckSittingPositionActivity::class.java)) }
    }
}