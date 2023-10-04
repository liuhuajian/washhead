package com.medical.health.device.ui

import android.content.Intent
import com.medical.health.device.base.BaseActivity
import com.medical.health.device.databinding.ActivityPushChairBinding
import com.medical.health.device.kt_extension.click
import com.medical.health.device.kt_extension.clickWithTrigger

class PushChairActivity:BaseActivity() {
    override val bind by getBind<ActivityPushChairBinding>()

    override fun initView() {
        super.initView()
        bind.includeTitleview.tvBack.click {finish()}
        bind.includeTitleview.tvTitle.text = "推入转移椅并复位"
        bind.tvConfirm.clickWithTrigger { startActivity(Intent(this, CheckSittingPositionActivity::class.java)) }
    }
}