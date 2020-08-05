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
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.oe.nik.krujzgergely.R
import com.oe.nik.krujzgergely.controllers.logincontroller.GoogleLogin
import kotlinx.android.synthetic.main.progress_logout_dialog_view.view.*

class CustomLogoutProgressDialog
{
    private lateinit var dialog: CustomDialog
    private lateinit var indeterminateDrawable : Drawable
    private var colorFromResources : Int = 0
    private val transparentWhiteColor = "#FFFFFFFF"

    fun showCustomLogoutProgressDialog(context: Context, title: CharSequence?): Dialog
    {
        val view = setView(context)

        setTitleIfItIsNotNull(view,title)

        view.customLogoutCardview.setCardBackgroundColor(getColorFromString())

        setColorFilterParameters(view, context)
        setColorFilter(indeterminateDrawable,colorFromResources)

        view.customProgressBarTitle.setTextColor(Color.BLACK)

        setDialog(context,view)

        return dialog
    }

    private fun setView(context: Context) : View
    {
        val inflater = (context as Activity).layoutInflater
        return inflater.inflate(R.layout.progress_logout_dialog_view, null)
    }

    private fun setTitleIfItIsNotNull(view : View, title: CharSequence?)
    {
        if (checkIfTitleIsNull(title)) { view.customProgressBarTitle.text = title }
    }

    private fun checkIfTitleIsNull(title : CharSequence?) : Boolean = title != null

    private fun getColorFromString() : Int = Color.parseColor(transparentWhiteColor)

    private fun setColorFilterParameters(view: View,context: Context)
    {
        val colorForProgressBar = selectYoutubeRedOrSpotifyGreenColorBasedOnWhichAccountTheUserLoggedIn()
        colorFromResources = ResourcesCompat.getColor(context.resources, colorForProgressBar, null)
        indeterminateDrawable = view.customProgressBar.indeterminateDrawable
    }

    private fun selectYoutubeRedOrSpotifyGreenColorBasedOnWhichAccountTheUserLoggedIn(): Int = when(GoogleLogin.googleAccount)
    {
        null -> R.color.spotifygreen
        else -> R.color.youtubeRed
    }

    private fun setColorFilter(drawable: Drawable, color: Int) =
        if (checkIfCurrentSDKVersionIsGreaterOrEqualsWithAndroidQ())
        {
            drawable.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
        }
        else
        {
            @Suppress("DEPRECATION")
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }

    private fun checkIfCurrentSDKVersionIsGreaterOrEqualsWithAndroidQ() : Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

    private fun setDialog(context: Context, view : View)
    {
        dialog = CustomDialog(context)

        dialog.apply {
            setContentView(view)
            show()
        }
    }
}