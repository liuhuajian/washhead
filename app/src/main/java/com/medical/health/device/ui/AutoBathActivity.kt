package com.medical.health.device.ui

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.medical.health.device.R
import com.medical.health.device.base.BaseActivity
import com.medical.health.device.databinding.ActivityAdjustArmAndWaterBinding
import com.medical.health.device.databinding.ActivityAdjustWashHeadBinding
import com.medical.health.device.databinding.ActivityAutoBathBinding
import com.medical.health.device.databinding.ActivityPushChairBinding
import com.medical.health.device.kt_extension.click
import com.medical.health.device.kt_extension.clickWithTrigger

class AutoBathActivity:BaseActivity() {
    override val bind by getBind<ActivityAutoBathBinding>()

    override fun initView() {
        super.initView()
        bind.includeTitleview.tvBack.click {finish()}
        bind.includeTitleview.tvTitle.text = "自动洗浴"
        bind.tvConfirm.clickWithTrigger { startActivity(Intent(this, BathCompleteActivity::class.java)) }
        initItemView()
    }

    private fun initItemView() {
        bind.includeBackupClean.run {
            ivTitle.setImageResource(R.mipmap.ic_backside)
            tvTitle.text = "背部清洁"
        }
        bind.includeArmClean.run {
            ivTitle.setImageResource(R.mipmap.ic_arm_strong)
            tvTitle.text = "手臂清洁"
        }
        bind.includeHandClean.run {
            ivTitle.setImageResource(R.mipmap.ic_hand_hold)
            tvTitle.text = "手持清洁"
        }
    }

    override fun initData() {
        super.initData()
        bind.seekbar.progress = 60
    }
}