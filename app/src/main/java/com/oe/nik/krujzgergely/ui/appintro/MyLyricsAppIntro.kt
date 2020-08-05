package com.oe.nik.krujzgergely.ui.appintro

import android.content.Intent
import android.graphics.Color
import android.graphics.Color.blue
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroPageTransformerType
import com.oe.nik.krujzgergely.R
import com.oe.nik.krujzgergely.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_app_intro.*

class MyLyricsAppIntro : AppCompatActivity(), ViewPager.OnPageChangeListener {

    //the needed attributes
    private var mPreferencesManager: PreferencesManager? = null
    private val mLayouts: Array<Int> = arrayOf(R.layout.app_intro_slide_one,R.layout.app_intro_slide_two,R.layout.app_intro_slide_three,
                                               R.layout.app_intro_slide_four,R.layout.app_intro_slide_five,R.layout.app_intro_slide_six,
                                               R.layout.app_intro_slide_seven, R.layout.app_intro_slide_eight,R.layout.app_intro_slide_nine)

    private lateinit var adapter : IntroPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_intro)

        mPreferencesManager = PreferencesManager(this)

        if (mPreferencesManager?.isNotFirstTimeLaunched()!!) { launchMainScreen() }

        //checking if the sdk is grate than Lollipop
        if (Build.VERSION.SDK_INT >= 21)
        {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

        //changing the color of the status bar to transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = Color.TRANSPARENT
        }

        adapter = IntroPagerAdapter(this, mLayouts)
        welcome_view_pager.adapter = adapter
        welcome_view_pager.addOnPageChangeListener(this)

        setupIndicators()
        setCurrentIndicator(0)

        //when the skip button is Pressed
        skip_button.setOnClickListener { launchMainScreen() }

        //when the next button is pressed
        next_button.setOnClickListener {
            val current = getItem()
            if (current < mLayouts.size) { welcome_view_pager.currentItem = current }
            else { launchMainScreen() }
        }
    }

    private fun launchMainScreen()
    {
        mPreferencesManager?.setFirstTimeLaunched()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun getItem(): Int = welcome_view_pager.currentItem + 1

    private fun setupIndicators()
    {
        val indicators = arrayOfNulls<ImageView>(adapter.count)
        val layoutParams : LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices)
        {
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(ContextCompat.getDrawable(applicationContext,R.drawable.indicator_inactive))
                this?.layoutParams = layoutParams
            }
            indicatorsContainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index : Int)
    {
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount)
        {
            val imageView = indicatorsContainer[i] as ImageView
            if (i == index)
            {
                imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.indicator_active))
            }
            else
            {
                imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.indicator_inactive))
            }
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        if (position == mLayouts.size - 1)
        {
            next_button.text = getString(R.string.done)
            skip_button.visibility = View.GONE
        } else
        {
            next_button.text = getString(R.string.next)
            skip_button.visibility = View.VISIBLE
        }
    }

    override fun onPageSelected(position: Int) { setCurrentIndicator(position) }

    override fun onPageScrollStateChanged(state: Int) {}
}
