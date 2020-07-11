package com.oe.nik.krujzgergely.ui.lyricsItem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.oe.nik.krujzgergely.R
import com.oe.nik.krujzgergely.databinding.ActivityLyricsItemBinding

class LyricsItemActiviy : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lyrics_item)

        val lyricsItemViewModel  = ViewModelProvider(this).get(LyricsItemActivityViewModel::class.java)

        DataBindingUtil.setContentView<ActivityLyricsItemBinding>(this,R.layout.activity_lyrics_item).apply {
            this.lifecycleOwner = this@LyricsItemActiviy
            this.lyricsItemModel = lyricsItemViewModel
        }
    }
}