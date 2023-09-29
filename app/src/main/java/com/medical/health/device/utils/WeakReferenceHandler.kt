package com.medical.health.device.utils

import android.os.Handler
import android.os.Message
import com.medical.health.device.base.IBaseView
import java.lang.ref.WeakReference

/**
 * 防止持有外部Activity引用造成内存泄露。
 */
abstract class WeakReferenceHandler(reference: IBaseView?) : Handler() {
    private val mReference: WeakReference<IBaseView?> = WeakReference(reference)
    override fun handleMessage(msg: Message) {
        if (mReference.get() == null) return
        handleMessage(mReference.get(), msg)
    }

    protected abstract fun handleMessage(
        reference: IBaseView?,
        msg: Message?
    )

}