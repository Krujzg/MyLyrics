package com.oe.nik.krujzgergely.ui.lyrics

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.oe.nik.krujzgergely.R
import com.oe.nik.krujzgergely.ui.createlyricsitem.CreateLyricsActivity
import com.oe.nik.krujzgergely.ui.lyricsItem.LyricsItemActiviy
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView


class LyricsesActivity : AppCompatActivity(), IonLyricsSelected {

    private lateinit var lyricsViewModel : LyricsActivityViewModel

    private lateinit var allLyricsActivityAdapter : LyricsesActivityAdapter
    private lateinit var favoritelyricsActivityAdapter : LyricsesActivityAdapter

    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var floatingActionButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lyricses)

        setOnClickListenerForFloatingActionButton()

        allLyricsActivityAdapter = LyricsesActivityAdapter(this, mutableListOf())
        favoritelyricsActivityAdapter = LyricsesActivityAdapter(this, mutableListOf())

        lyricsViewModel = ViewModelProvider(this).get(LyricsActivityViewModel::class.java)

        loadLyricsMultiSnapRecyclerView("All")
        loadLyricsMultiSnapRecyclerView("Favorite")
    }

    private fun setOnClickListenerForFloatingActionButton()
    {
        floatingActionButton = findViewById<FloatingActionButton>(R.id.floating_action_button_create)
        floatingActionButton.setOnClickListener{
            startActivity(Intent(this,CreateLyricsActivity::class.java))
        }
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
                                  lyricsActivityAdapter : LyricsesActivityAdapter)
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