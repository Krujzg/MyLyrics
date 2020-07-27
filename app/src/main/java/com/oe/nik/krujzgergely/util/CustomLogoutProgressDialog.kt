package com.oe.nik.krujzgergely.util

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.core.content.res.ResourcesCompat
import com.oe.nik.krujzgergely.R
import com.oe.nik.krujzgergely.controllers.logincontroller.GoogleLogin
import kotlinx.android.synthetic.main.progress_logout_dialog_view.view.*

class CustomLogoutProgressDialog {

    lateinit var dialog: CustomDialog

    fun show(context: Context): Dialog {
        return show(context, null)
    }

    fun show(context: Context, title: CharSequence?): Dialog {
        val inflater = (context as Activity).layoutInflater
        val view = inflater.inflate(R.layout.progress_logout_dialog_view, null)
        if (title != null) {
            view.cp_title.text = title
        }

        // Card Color
        view.cp_cardview.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"))

        // Progress Bar Color
        val colorForProgressBar  = selectYoutubeRedOrSpotifyGreenColorBasedOnwithWhichAccountTheUserLoggedIn()
        setColorFilter(view.cp_pbar.indeterminateDrawable, ResourcesCompat.getColor(context.resources, colorForProgressBar, null))

        // Text Color
        view.cp_title.setTextColor(Color.BLACK)

        dialog = CustomDialog(context)
        dialog.setContentView(view)
        dialog.show()
        return dialog
    }

    private fun setColorFilter(drawable: Drawable, color: Int) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            drawable.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
        } else {
            @Suppress("DEPRECATION")
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }

    private fun selectYoutubeRedOrSpotifyGreenColorBasedOnwithWhichAccountTheUserLoggedIn()
            : Int = when(GoogleLogin.googleAccount)
            {
                null -> R.color.spotifygreen
                else -> R.color.youtubeRed
            }

    class CustomDialog(context: Context) : Dialog(context, R.style.CustomDialogTheme) {
        init {
            // Set Semi-Transparent Color for Dialog Background
            //window?.decorView?.rootView?.setBackgroundResource(R.color.semitransparentdarkgrey)
            window?.decorView?.setOnApplyWindowInsetsListener { _, insets ->
                insets.consumeSystemWindowInsets()
            }
        }
    }
}