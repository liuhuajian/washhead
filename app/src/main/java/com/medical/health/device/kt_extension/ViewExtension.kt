package com.medical.health.device.kt_extension

import android.content.Context
import android.view.View

/**
 * @Author: censheng
 * @CreateDate: 2021/11/25 10:57
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/11/25 10:57
 * @UpdateRemark: 更新说明
 * @Version: 0.1
 */

fun <T : View> T.withTrigger(delay: Long = 800): T {
    triggerDelay = delay
    return this
}

/***
 * 点击事件的View扩展
 * @param block: (T) -> Unit 函数
 * @return Unit
 */
fun <T : View> T.click(block: (T) -> Unit) = setOnClickListener {
//    ClickUtils.applyPressedViewAlpha(this,0.8f)
    if (clickEnable()) {
        block(it as T)
    }
}

/***
 * 带延迟过滤的点击事件View扩展
 * @param delay Long 延迟时间，默认800毫秒
 * @param block: (T) -> Unit 函数
 * @return Unit
 */
fun <T : View> T.clickWithTrigger(time: Long = 800, block: (T) -> Unit) {
    // 此处是点击后按钮背景透明度变化 可参考blankj开源工具类
//    ClickUtils.applyPressedViewAlpha(this,0.6f)
    triggerDelay = time
    setOnClickListener {
        if (clickEnable()) {
            block(it as T)
        }
    }
}

private var <T : View> T.triggerLastTime: Long
    get() = if (getTag(1123460103) != null) getTag(1123460103) as Long else 0
    set(value) {
        setTag(1123460103, value)
    }

private var <T : View> T.triggerDelay: Long
    get() = if (getTag(1123461123) != null) getTag(1123461123) as Long else -1
    set(value) {
        setTag(1123461123, value)
    }

private fun <T : View> T.clickEnable(): Boolean {
    var flag = false
    val currentClickTime = System.currentTimeMillis()
    if (currentClickTime - triggerLastTime >= triggerDelay) {
        flag = true
    }
    triggerLastTime = currentClickTime
    return flag
}

// 两次点击间隔默认值
private const val TWICE_INTERVAL = 500L
private var sLastClickTime: Long = 0
/**
 * 是否有效触发，2次点击默认间隔500毫秒
 *
 * @return true 有效，false 无效
 */
fun Context.validShot(): Boolean {
    val currentTime = System.currentTimeMillis()
    // 大于两次点击的间隔，返回 true 有效点击
    if (currentTime - sLastClickTime > TWICE_INTERVAL) {
        sLastClickTime = currentTime
        return true
    }
    return false

}

