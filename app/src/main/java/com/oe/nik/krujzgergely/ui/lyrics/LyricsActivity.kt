package com.oe.nik.krujzgergely.ui.lyrics

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.oe.nik.krujzgergely.R
import com.oe.nik.krujzgergely.ui.createlyricsitem.CreateLyricsActivity
import com.oe.nik.krujzgergely.ui.lyricsItem.LyricsItemActiviy
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView


class LyricsActivity : AppCompatActivity(), IonLyricsSelected {

    private lateinit var lyricsViewModel : LyricsActivityViewModel

    private lateinit var allLyricsActivityAdapter : LyricsActivityAdapter
    private lateinit var favoritelyricsActivityAdapter : LyricsActivityAdapter

    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var floatingActionButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lyricses)

        allLyricsActivityAdapter = LyricsActivityAdapter(this, mutableListOf())
        favoritelyricsActivityAdapter = LyricsActivityAdapter(this, mutableListOf())

        lyricsViewModel = ViewModelProvider(this).get(LyricsActivityViewModel::class.java)

        loadLyricsMultiSnapRecyclerView("All")
        loadLyricsMultiSnapRecyclerView("Favorite")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.lyrics_activity_menu_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId)
        {
            R.id.CreateNewLyrics -> {
                startActivity(Intent(this,CreateLyricsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun loadLyricsMultiSnapRecyclerView(typeOfTheRecycler: String)
    {
        linearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        when(typeOfTheRecycler)
        {
            "All" ->
            {
                val allLyricsRecyclerView = findViewById<MultiSnapRecyclerView>(R.id.all_recycler_view)
                setRecyclerViewComponents(allLyricsRecyclerView,typeOfTheRecycler,allLyricsActivityAdapter)
            }
            "Favorite" ->
            {
                val favouriteLyricsRecyclerView = findViewById<MultiSnapRecyclerView>(R.id.favourite_recycler_view)
                setRecyclerViewComponents(favouriteLyricsRecyclerView,typeOfTheRecycler,favoritelyricsActivityAdapter)
            }
        }
    }

    private fun setRecyclerViewComponents(currentRecyclerView: MultiSnapRecyclerView,typeOfTheRecycler: String,
                                  lyricsActivityAdapter : LyricsActivityAdapter)
    {
        currentRecyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = lyricsActivityAdapter
        }

        getDataFromTheViewModel(typeOfTheRecycler)
    }

    private fun getDataFromTheViewModel(typeOfTheRecycler : String)
    {
        when(typeOfTheRecycler)
        {
            "All" ->
            {
                lyricsViewModel.getAllLyricsFromLocalDB().observe(this, Observer {allLyricsList ->
                    allLyricsActivityAdapter.swapData(allLyricsList)
                })
            }
            "Favorite" ->
            {
               lyricsViewModel.getFavoriteLyricsFromLocalDB().observe(this, Observer {favoritelyricsList ->
                   favoritelyricsActivityAdapter.swapData(favoritelyricsList)
                })
            }
        }
    }

    override fun onLyricsSelected() { startActivity( Intent(this, LyricsItemActiviy::class.java)) }
}