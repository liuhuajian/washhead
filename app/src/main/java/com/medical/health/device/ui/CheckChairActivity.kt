package com.medical.health.device.ui

import android.content.Intent
import com.medical.health.device.R
import com.medical.health.device.base.BaseActivity
import com.medical.health.device.databinding.ActivityCheckChairBinding
import com.medical.health.device.kt_extension.click
import com.medical.health.device.kt_extension.clickWithTrigger

class CheckChairActivity:BaseActivity() {
    override val bind by getBind<ActivityCheckChairBinding>()
    private var index = -1

    override fun initView() {
        super.initView()
        bind.includeTitleview.tvTitle.text ="检测坐姿高度"
        bind.includeTitleview.tvBack.click {finish()}
        bind.tvHigh1.click {
            removeAllState()
            index = 0
            bind.tvHigh1.setBackgroundResource(R.drawable.bg_checked)
        }
        bind.tvHigh2.click {
            removeAllState()
            index = 1
            bind.tvHigh2.setBackgroundResource(R.drawable.bg_checked)
        }
        bind.tvHigh3.click {
            removeAllState()
            index = 2
            bind.tvHigh3.setBackgroundResource(R.drawable.bg_checked)
        }
        bind.tvHigh4.click {
            removeAllState()
            index = 3
            bind.tvHigh4.setBackgroundResource(R.drawable.bg_checked)
        }
        bind.tvHigh5.click {
            removeAllState()
            index = 4
            bind.tvHigh5.setBackgroundResource(R.drawable.bg_checked)
        }
        bind.tvConfirm.clickWithTrigger { startActivity(Intent(this, PushChairActivity::class.java)) }
    }

    private fun removeAllState(){
        bind.tvHigh1.setBackgroundResource(R.drawable.bg_border_83838)
        bind.tvHigh2.setBackgroundResource(R.drawable.bg_border_83838)
        bind.tvHigh3.setBackgroundResource(R.drawable.bg_border_83838)
        bind.tvHigh4.setBackgroundResource(R.drawable.bg_border_83838)
        bind.tvHigh5.setBackgroundResource(R.drawable.bg_border_83838)
    }
}