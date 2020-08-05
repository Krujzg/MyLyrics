package com.oe.nik.krujzgergely.ui.appintro

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager.widget.ViewPager
import com.oe.nik.krujzgergely.R
import com.oe.nik.krujzgergely.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_app_intro.*

class MyLyricsAppIntro : AppCompatActivity(), ViewPager.OnPageChangeListener
{
    private var mPreferencesManager: PreferencesManager? = null
    private val mLayouts: Array<Int> = getAppIntroLayouts

    private lateinit var introPagerAdapter: IntroPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_intro)

        mPreferencesManager = PreferencesManager(this)

        checkIfTheAppWasNotLaunchedForTheFirstTimeThenIfItsTrueLaunchMainScreen()
        setWindowUiToFullScreenWithTransparentStatusBar()
        introPagerAdapter = IntroPagerAdapter(this, mLayouts)

        welcome_view_pager.apply {
            adapter = introPagerAdapter
        }.addOnPageChangeListener(this)

        setupIndicators()
        setCurrentIndicator(0)

        skip_button.setOnClickListener { launchMainScreen() }
        nextButtonOnClickListener()
    }

    private fun nextButtonOnClickListener()
    {
        next_button.setOnClickListener {
            val currentItemIndex = getCurrentItemIndex()
            when(checkIfTheCurrentItemIndexIsLowerThanTheLayoutsSize(currentItemIndex))
            {
                true -> welcome_view_pager.currentItem = currentItemIndex
                false -> launchMainScreen()
            }
        }
    }

    private fun checkIfTheCurrentItemIndexIsLowerThanTheLayoutsSize(currentItemInTheViewPager : Int) :
            Boolean = currentItemInTheViewPager < mLayouts.size

    private fun setWindowUiToFullScreenWithTransparentStatusBar()
    {
        window.apply {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }

    private fun checkIfTheAppWasNotLaunchedForTheFirstTimeThenIfItsTrueLaunchMainScreen()
    {
        if (mPreferencesManager?.isNotFirstTimeLaunched()!!) { launchMainScreen() }
    }

    private fun launchMainScreen()
    {
        mPreferencesManager?.setFirstTimeLaunched()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun getCurrentItemIndex(): Int = welcome_view_pager.currentItem + 1

    private fun setupIndicators()
    {
        val indicators = arrayOfNulls<ImageView>(introPagerAdapter.count)
        val layoutParams : LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices)
        {
            addIndicatiorToIndicatorContainer(indicators,i,layoutParams)
        }
    }
    private fun addIndicatiorToIndicatorContainer(indicators: Array<ImageView?>, index : Int, layoutParams: LinearLayout.LayoutParams)
    {
        indicators[index] = ImageView(applicationContext)
        indicators[index].apply {
            this?.setImageDrawable(ContextCompat.getDrawable(applicationContext,R.drawable.indicator_inactive))
            this?.layoutParams = layoutParams
        }
        indicatorsContainer.addView(indicators[index])
    }

    private fun setCurrentIndicator(index : Int)
    {
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount)
        {
            val imageView = indicatorsContainer[i] as ImageView
            when(i)
            {
                index -> imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.indicator_active))
                else -> imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.indicator_inactive))
            }
        }
    }

    private fun checkIfTheCurrentPositionIsTheLastOne(position: Int) : Boolean = position == mLayouts.size - 1

    private fun setThePageButtonsByThePagePosition(nextButtonText : String, viewVisibility : Int)
    {
        next_button.text = nextButtonText
        skip_button.visibility = viewVisibility
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

        when(checkIfTheCurrentPositionIsTheLastOne(position))
        {
            true -> setThePageButtonsByThePagePosition(getString(R.string.done),View.GONE)
            false -> setThePageButtonsByThePagePosition(getString(R.string.next),View.VISIBLE)
        }
    }

    override fun onPageSelected(position: Int) { setCurrentIndicator(position) }

    override fun onPageScrollStateChanged(state: Int) {}
}
