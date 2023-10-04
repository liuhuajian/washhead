package com.medical.health.device.ui

import com.medical.health.device.base.BaseActivity
import com.medical.health.device.databinding.ActivityTestFunctionBinding
import com.medical.health.device.kt_extension.click

class TestFunctionActivity:BaseActivity() {
    override val bind by getBind<ActivityTestFunctionBinding>()

    override fun initView() {
        super.initView()
        bind.tvConfirm.click { finish() }
    }
}