package com.oe.nik.krujzgergely.ui.appintro

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.viewpager.widget.PagerAdapter

class IntroPagerAdapter(val context: Context, private val layouts: Array<Int>) : PagerAdapter() {


    override fun instantiateItem(@NonNull container: ViewGroup, position: Int): Any {

        val layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater;


        val view = layoutInflater.inflate(layouts[position], container, false);
        container.addView(view);

        return view;
    }

    override fun getCount(): Int = layouts.size


    override fun isViewFromObject(view: View, obj: Any): Boolean = view == obj


    override fun  destroyItem(container : ViewGroup, position :Int, obj : Any) {
        val view = obj as View
        container.removeView(view)
    }

}