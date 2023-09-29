package com.medical.health.device.utils

import android.util.Log
import com.medical.health.device.utils.MyLogger
import org.jetbrains.annotations.NotNull
import java.lang.Exception

/**
 * 作    者：julyzeng （曾招林)  364298140@qq.com
 * 版    本：1.0
 * 创建日期：2023/9/28  9:27
 * 描    述： 日志管理类
 */
object MyLogger {
    var className: String? = null
    var methodName: String? = null
    var lineNumber = 0

    //        return BuildConfig.isDebug;
    private val isDebuggable: Boolean
        get() = true

    private fun createLogTag(): String {
        val buffer = StringBuffer()
        buffer.append(className)
        buffer.append("[")
        buffer.append(methodName)
        buffer.append(":")
        buffer.append(lineNumber)
        buffer.append("]")
        return buffer.toString()
    }

    private fun getMethodNames(sElements: Array<StackTraceElement>) {
        try {
//            className = getCurActivityInfo(sElements[2]) + sElements[1].getFileName().split("\\.")[0];
            className = sElements[1].fileName
            methodName = sElements[1].methodName
            lineNumber = sElements[1].lineNumber
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun e(message: String?) {
        if (!isDebuggable) return

        // Throwable instance must be created before any methods
        getMethodNames(Throwable().stackTrace)
        if (message == null) {
            Log.e(createLogTag(), message!!)
            return
        }
        for (i in 0 until message.length / 2048 + 1) {
            if (message.length < 2048 * (i + 1)) {
                Log.e(createLogTag(), message.substring(2048 * i, message.length))
            } else {
                Log.e(createLogTag(), message.substring(2048 * i, 2048 * (i + 1)))
            }
        }
    }

    fun i(message: String?) {
        if (!isDebuggable) return
        getMethodNames(Throwable().stackTrace)
        Log.i(createLogTag(), message!!)
    }

    fun d(message: String?) {
        if (!isDebuggable) return
        getMethodNames(Throwable().stackTrace)
        Log.d(createLogTag(), message!!)
    }

    fun v(message: String?) {
        if (!isDebuggable) return
        getMethodNames(Throwable().stackTrace)
        Log.v(createLogTag(), message!!)
    }

    fun w(message: String?) {
        if (!isDebuggable) return
        getMethodNames(Throwable().stackTrace)
        Log.w(createLogTag(), message!!)
    }
}