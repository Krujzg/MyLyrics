package com.oe.nik.krujzgergely.ui.launcher

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.oe.nik.krujzgergely.R
import com.oe.nik.krujzgergely.ui.appintro.MyLyricsAppIntro
import com.oe.nik.krujzgergely.ui.appintro.PreferencesManager
import com.oe.nik.krujzgergely.ui.main.MainActivity

class LauncherActivity : AppCompatActivity() {

    companion object { const val SPLASH_TIME = 3000L }
    private var classes = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        classes = 1

        setWindowUiToFullScreenWithTransparentStatusBar()

        setClassesToTwoIfTheAppWasLaunchedBefore()

        delayAppStartToBeAbleToSeeAppLaunchPage()
    }

    private fun setWindowUiToFullScreenWithTransparentStatusBar()
    {
        window.apply {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = Color.TRANSPARENT
        }
    }

    private fun setClassesToTwoIfTheAppWasLaunchedBefore()
    {
        if (PreferencesManager(this).isNotFirstTimeLaunched()) { classes = 2 }
    }

    private fun delayAppStartToBeAbleToSeeAppLaunchPage()
    {
        Handler().postDelayed({
                decideWhichActivityToStartByCheckingIfTheAppWasLaunchedBefore()
                finish()
            }, SPLASH_TIME)
    }

    private fun decideWhichActivityToStartByCheckingIfTheAppWasLaunchedBefore()
    {
        when(classes)
        {
            1 -> startActivity(Intent(this, MyLyricsAppIntro::class.java))
            else -> startActivity(Intent(this, MainActivity::class.java))
        }
    }
}