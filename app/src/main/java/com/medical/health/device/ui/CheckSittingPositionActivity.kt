package com.medical.health.device.ui

import android.content.Intent
import com.medical.health.device.base.BaseActivity
import com.medical.health.device.databinding.ActivityCheckSitBinding
import com.medical.health.device.kt_extension.click
import com.medical.health.device.kt_extension.clickWithTrigger

class CheckSittingPositionActivity:BaseActivity() {
    override val bind by getBind<ActivityCheckSitBinding>()

    override fun initView() {
        super.initView()
        bind.includeTitleview.tvBack.click { finish() }
        bind.includeTitleview.tvTitle.text = "检查坐姿  插氧气"
        bind.tvConfirm.clickWithTrigger {
            startActivity(Intent(this, AdjustWashHeadActivity::class.java))
        }
    }
}