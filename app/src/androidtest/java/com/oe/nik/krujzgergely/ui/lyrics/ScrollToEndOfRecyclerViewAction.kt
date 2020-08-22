package com.oe.nik.krujzgergely.ui.lyrics

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.hamcrest.Matchers.allOf

class ScrollToEndOfRecyclerViewAction : ViewAction {
    override fun getDescription(): String { return "scroll RecyclerView to end" }

    override fun getConstraints(): org.hamcrest.Matcher<View> { return allOf(isAssignableFrom(RecyclerView::class.java), isDisplayed()) }

    override fun perform(uiController: UiController?, view: View?) {
        val recyclerView = view as RecyclerView
        val itemCount = recyclerView.adapter?.itemCount
        val position = itemCount ?: 0
        recyclerView.scrollToPosition(position)
        uiController?.loopMainThreadUntilIdle()
    }
}