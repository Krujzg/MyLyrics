package com.oe.nik.krujzgergely.ui.lyricsItem

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.oe.nik.krujzgergely.R
import com.oe.nik.krujzgergely.databinding.ActivityLyricsItemBinding
import com.oe.nik.krujzgergely.ui.lyrics.LyricsActivity
import com.oe.nik.krujzgergely.ui.updatelyricsitem.UpdateLyricsItemActivity

class LyricsItemActiviy : AppCompatActivity()
{
    lateinit var lyricsItemViewModel : LyricsItemActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lyrics_item)

        lyricsItemViewModel  = ViewModelProvider(this).get(LyricsItemActivityViewModel::class.java)

        DataBindingUtil.setContentView<ActivityLyricsItemBinding>(this,R.layout.activity_lyrics_item).apply {
            this.lifecycleOwner = this@LyricsItemActiviy
            this.lyricsItemModel = lyricsItemViewModel
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.lyrics_item_menu_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId)
        {
            R.id.UpdateLyrics -> startUpdateLyricsItemActivity()
            R.id.DeleteLyrics -> deleteLyricsFromLocalDbThenReturnToLyricsActivity()
            else -> super.onOptionsItemSelected(item)
        }

    private fun startUpdateLyricsItemActivity() : Boolean
    {
        startActivity(Intent(this, UpdateLyricsItemActivity::class.java))
        return true
    }

    private fun deleteLyricsFromLocalDbThenReturnToLyricsActivity() : Boolean
    {
        lyricsItemViewModel.deleteLyricsFromLocalDb()
        startActivity(Intent(this, LyricsActivity::class.java))
        return true
    }
}