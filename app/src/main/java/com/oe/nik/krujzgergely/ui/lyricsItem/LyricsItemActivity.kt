package com.oe.nik.krujzgergely.ui.lyricsItem

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.oe.nik.krujzgergely.R
import com.oe.nik.krujzgergely.controllers.logincontroller.GoogleLogin
import com.oe.nik.krujzgergely.databinding.ActivityLyricsItemBinding
import com.oe.nik.krujzgergely.ui.lyrics.LyricsActivity
import com.oe.nik.krujzgergely.ui.updatelyricsitem.UpdateLyricsItemActivity


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
    }

    @SuppressLint("RestrictedApi")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater

        if (menu is MenuBuilder) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                menu.setOptionalIconsVisible(true)
            }
        }

        when(GoogleLogin.googleAccount)
        {
            null ->  inflater.inflate(R.menu.lyrics_item_menu_spotify_options, menu)
            else ->  inflater.inflate(R.menu.lyrics_item_menu_google_options, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {

        when(GoogleLogin.googleAccount)
        {
            null -> setSpotifyMenuOptionsActions(item)
            else -> setYoutubeMenuOptionsActions(item)
        }

        return true
    }

    private fun setSpotifyMenuOptionsActions(item : MenuItem)
    {
        when (item.itemId)
        {
            R.id.PlaySpotify -> lyricsItemViewModel.playSpotifyLink()
            R.id.Pause -> lyricsItemViewModel.pauseSpotifyLink()
            R.id.UpdateLyrics -> startUpdateLyricsItemActivity()
            R.id.DeleteLyrics -> deleteLyricsFromLocalDbThenReturnToLyricsActivity()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setYoutubeMenuOptionsActions(item : MenuItem)
    {
        when (item.itemId)
        {
            R.id.PlayYoutube -> openAndPlayCurrentYoutubeSong()
            R.id.PlayYoutubeMusic -> openAndPlayCurrentYoutubeMusicSong()
            R.id.UpdateLyrics -> startUpdateLyricsItemActivity()
            R.id.DeleteLyrics -> deleteLyricsFromLocalDbThenReturnToLyricsActivity()
            else -> super.onOptionsItemSelected(item)
        }
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
        intent.apply {
            data = Uri.parse(lyricsItemViewModel.YoutubeLink.value!!)
            setPackage("com.google.android.youtube")
        }
        startActivity(intent)
    }

    private fun openAndPlayCurrentYoutubeMusicSong()
    {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.apply {
            data = Uri.parse(lyricsItemViewModel.YoutubeMusicLink.value!!)
            setPackage("com.google.android.apps.youtube.music")
        }
        startActivity(intent)
    }

    override fun onBackPressed() {
        startActivity(Intent(this, LyricsActivity::class.java))
        overridePendingTransition( R.xml.slide_in_down ,R.xml.slide_out_down )
    }
}