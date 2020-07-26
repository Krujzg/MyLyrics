package com.oe.nik.krujzgergely.ui.lyricsItem

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.oe.nik.krujzgergely.R
import com.oe.nik.krujzgergely.databinding.ActivityLyricsItemBinding
import com.oe.nik.krujzgergely.generated.callback.OnItemSelected
import com.oe.nik.krujzgergely.ui.lyrics.LyricsActivity
import com.oe.nik.krujzgergely.ui.updatelyricsitem.UpdateLyricsItemActivity
import kotlinx.android.synthetic.main.activity_lyrics_item.*


class LyricsItemActivity : AppCompatActivity()
{
    lateinit var lyricsItemViewModel : LyricsItemActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lyrics_item)

        lyricsItemViewModel  = ViewModelProvider(this).get(LyricsItemActivityViewModel::class.java)

        DataBindingUtil.setContentView<ActivityLyricsItemBinding>(this,R.layout.activity_lyrics_item).apply {
            this.lifecycleOwner = this@LyricsItemActivity
            this.lyricsItemModel = lyricsItemViewModel
        }

        if (playYoutubeButton.isVisible) { playYoutubeButton.setOnClickListener{openAndPlayCurrentYoutubeSong()} }
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

    private fun openAndPlayCurrentYoutubeSong()
    {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(lyricsItemViewModel.YoutubeLink.value!!)
        intent.setPackage("com.google.android.youtube")
        startActivity(intent)
    }

    override fun onBackPressed() {
        startActivity(Intent(this, LyricsActivity::class.java))
        overridePendingTransition( R.xml.slide_in_down ,R.xml.slide_out_down )
    }
}