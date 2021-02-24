package com.gallotti.appcafebarrestaurante.extensions

import android.util.Log
import com.gallotti.appcafebarrestaurante.widget.FullscreenLoadingDialog

fun androidx.fragment.app.FragmentActivity.showLoadingDialog() {
    val manager = supportFragmentManager
    val previousDialog = manager.findFragmentByTag(FullscreenLoadingDialog.tag)
    if (previousDialog == null) {
        val loadingDialog = FullscreenLoadingDialog()
        loadingDialog.show(manager, FullscreenLoadingDialog.tag)
    }
}

fun androidx.fragment.app.FragmentActivity.hideLoadingDialog() {
    val manager = supportFragmentManager
    val previousDialog = manager
        .findFragmentByTag(FullscreenLoadingDialog.tag) as? FullscreenLoadingDialog
    previousDialog?.dismissAllowingStateLoss() ?: Log.e("","No dialogs to dismiss")
}