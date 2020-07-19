package com.oe.nik.krujzgergely.ui.lyricsItem

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
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
    lateinit var youtubePlayButton : Button
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lyrics_item)

        lyricsItemViewModel  = ViewModelProvider(this).get(LyricsItemActivityViewModel::class.java)
        youtubePlayButton =  findViewById<Button>(R.id.youtubePlayButton)

        DataBindingUtil.setContentView<ActivityLyricsItemBinding>(this,R.layout.activity_lyrics_item).apply {
            this.lifecycleOwner = this@LyricsItemActiviy
            this.lyricsItemModel = lyricsItemViewModel
        }
        youtubePlayButton.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(lyricsItemViewModel.YoutubeLink.value!!))

            intent.component = ComponentName("com.google.android.youtube", "com.google.android.youtube.PlayerActivity")

            val manager: PackageManager = packageManager
            val infos = manager.queryIntentActivities(intent, 0)
            if (infos.size > 0) { startActivity(intent) }
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

    override fun onBackPressed() {
        startActivity(Intent(this, LyricsActivity::class.java))
        overridePendingTransition( R.xml.slide_in_down ,R.xml.slide_out_down )
    }
}