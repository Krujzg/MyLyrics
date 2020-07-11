package com.oe.nik.krujzgergely.ui.updatelyricsitem

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.oe.nik.krujzgergely.R
import com.oe.nik.krujzgergely.databinding.ActivityUpdateLyricsitemBindingImpl
import com.oe.nik.krujzgergely.ui.lyrics.LyricsesActivity

class UpdateLyricsItemActivity : AppCompatActivity()
{
    lateinit var updateLyricsItemViewModel : UpdateLyricsItemViewModel
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_lyricsitem)

        updateLyricsItemViewModel  = ViewModelProvider(this).get(UpdateLyricsItemViewModel::class.java)

        DataBindingUtil.setContentView<ActivityUpdateLyricsitemBindingImpl>(this, R.layout.activity_update_lyricsitem).apply {
            this.lifecycleOwner = this@UpdateLyricsItemActivity
            this.updateLyricsItemModel = updateLyricsItemViewModel
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.update_lyrics_activity_menu_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId)
        {
            R.id.Save -> {
                updateLyricsItemViewModel.updateLyricsFromLocalDb()
                startActivity(Intent(this, LyricsesActivity::class.java))
                true
            }
            R.id.Cancel -> {
                startActivity(Intent(this, LyricsesActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}