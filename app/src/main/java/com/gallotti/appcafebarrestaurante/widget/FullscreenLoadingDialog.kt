package com.gallotti.appcafebarrestaurante.widget

import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import android.widget.ProgressBar
import com.gallotti.appcafebarrestaurante.R

class FullscreenLoadingDialog : androidx.fragment.app.DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullscreenDialog)
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            it.window!!.setLayout(width, height)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val contextWrapper = ContextThemeWrapper(context, R.style.AppTheme)
        val progressBar = ProgressBar(contextWrapper).apply {
            isIndeterminate = true
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER
            )
        }
        return FrameLayout(contextWrapper).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            addView(progressBar)
        }
    }

    companion object {
        const val tag: String = "FullscreenLoadingDialog"
    }

}