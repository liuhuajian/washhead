package com.medical.health.device.service

import android.bluetooth.BluetoothGatt
import com.clj.fastble.BleManager
import com.clj.fastble.callback.BleGattCallback
import com.clj.fastble.callback.BleScanCallback
import com.clj.fastble.data.BleDevice
import com.clj.fastble.exception.BleException
import com.clj.fastble.scan.BleScanRuleConfig
import com.medical.health.device.utils.MyLogger
import com.medical.health.device.utils.SharedPreferenceUtil

object BleManagerUtils {

    private var mBleDevice:BleDevice?=null

    fun getConnectedDevice():BleDevice?{
        return mBleDevice
    }

    fun judgeConnectedBle():Boolean{
        getConnectedDeviceFormer()?.takeIf { it.isNotEmpty() }?.run {
            BleManager.getInstance().bluetoothAdapter.getRemoteDevice(this)?.run {
                return BleManager.getInstance().isConnected(BleDevice(this))
            }
        }
        return false
    }

    /**
     * 初始化并且扫描设备
     */
    fun initAndStartScan(deviceName:String?,callBack:(BleDevice?) ->Unit) {
        BleManager.getInstance().initScanRule(
            BleScanRuleConfig.Builder()
//                .setDeviceName(true,deviceName)
                .setScanTimeOut(10000)
                .build()
        )
        BleManager.getInstance().scan(object : BleScanCallback() {
            override fun onScanStarted(success: Boolean) {

            }

            override fun onScanning(bleDevice: BleDevice?) {
                MyLogger.e("onScanning-->${bleDevice?.name}")
                callBack.invoke(bleDevice)
//                connectDevice(bleDevice)
            }

            override fun onScanFinished(scanResultList: MutableList<BleDevice>?) {

            }
        })
    }

    private var bleGattCallBack = object :BleGattCallback(){
        override fun onStartConnect() {

        }

        override fun onDisConnected(
            isActiveDisConnected: Boolean,
            device: BleDevice?,
            gatt: BluetoothGatt?,
            status: Int
        ) {
            MyLogger.e("onDisConnected")
            mBleDevice = null
        }

        override fun onConnectSuccess(bleDevice: BleDevice, gatt: BluetoothGatt, status: Int) {
            MyLogger.e("onConnectSuccess")
            mBleDevice = bleDevice
        }

        override fun onConnectFail(bleDevice: BleDevice, exception: BleException?) {
            MyLogger.e("onConnectFail-->" + exception.toString())

        }
    }

    /**
     * 连接指定设备
     */
    fun connectDevice(bleDevice: BleDevice?) {
        BleManager.getInstance().connect(bleDevice, bleGattCallBack)
    }

    /**
     * 断开指定设备
     */
    fun disConnectDevice(bleDevice: BleDevice?) {
        BleManager.getInstance().disconnect(bleDevice)
    }

    /**
     * 获取之前连接过的设备
     */
    fun getConnectedDeviceFormer():String?{
        return SharedPreferenceUtil.getSpString(SharedPreferenceUtil.MAC_ADDRESS)
    }

    fun restartConnectDevice(){
        getConnectedDeviceFormer()?.takeIf { it.isNotEmpty() }?.run {
            BleManager.getInstance().bluetoothAdapter.getRemoteDevice(this)?.run {
                BleManager.getInstance().connect(BleDevice(this),bleGattCallBack)
            }

        }
    }

    /**
     * 释放资源
     */
    fun destroy(){
        BleManager.getInstance().destroy()
    }
}