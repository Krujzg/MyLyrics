package com.oe.nik.krujzgergely.util

import android.app.Dialog
import android.content.Context
import com.oe.nik.krujzgergely.R

class CustomDialog(context: Context) : Dialog(context, R.style.CustomDialogTheme) {
    init {
        window?.decorView?.setOnApplyWindowInsetsListener { _, insets ->
            insets.consumeSystemWindowInsets()
        }
    }
}