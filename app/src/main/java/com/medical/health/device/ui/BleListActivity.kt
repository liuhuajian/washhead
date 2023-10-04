package com.medical.health.device.ui

import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.clj.fastble.data.BleDevice
import com.medical.health.device.R
import com.medical.health.device.base.BaseActivity
import com.medical.health.device.databinding.ActivityBleListBinding
import com.medical.health.device.kt_extension.clickWithTrigger
import com.medical.health.device.service.BleManagerUtils

class BleListActivity:BaseActivity() {
    override val bind by getBind<ActivityBleListBinding>()

    override fun initView() {
        super.initView()
        initRecyclerView()
    }

    private var bleAdapter:BaseQuickAdapter<BleDevice,BaseViewHolder>?=null

    private fun initRecyclerView() {
        bind.bleRecyclerview.run {
            layoutManager = LinearLayoutManager(this@BleListActivity)
            bleAdapter = object :BaseQuickAdapter<BleDevice,BaseViewHolder>(R.layout.item_ble_list){
                override fun convert(holder: BaseViewHolder, item: BleDevice) {
                    holder.setText(R.id.tv_name,item.mac +" "+ if (item.name==null) "" else item.name)
                    holder.getView<TextView>(R.id.tv_status)?.run {
                        if (BleManagerUtils.getConnectedDevice()?.mac ==item.mac){
                            text = "已连接"
                            clickWithTrigger {
                                BleManagerUtils.disConnectDevice(item)
                            }
                        }else{
                            text = "未连接"
                            clickWithTrigger {
                                BleManagerUtils.connectDevice(item)
                            }
                        }
                    }
                }

            }
            adapter = bleAdapter
        }

    }

    override fun initData() {
        super.initData()
        BleManagerUtils.initAndStartScan(null) {
            it?.run {
                bleAdapter?.addData(this)
            }
        }
    }
}