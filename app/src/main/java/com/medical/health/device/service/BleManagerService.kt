package com.medical.health.device.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

class BleManagerService:Service() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)

    }


    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}