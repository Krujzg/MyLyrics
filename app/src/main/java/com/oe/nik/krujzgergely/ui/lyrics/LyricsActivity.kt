package com.oe.nik.krujzgergely.ui.lyrics

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.oe.nik.krujzgergely.R
import com.oe.nik.krujzgergely.controllers.logincontroller.GoogleLogin
import com.oe.nik.krujzgergely.controllers.logincontroller.SpotifyLogin
import com.oe.nik.krujzgergely.models.LyricsModel
import com.oe.nik.krujzgergely.models.enums.TypeOfTheRecycler
import com.oe.nik.krujzgergely.ui.accountInfo.AccountInfoActivity
import com.oe.nik.krujzgergely.ui.createlyricsitem.CreateLyricsActivity
import com.oe.nik.krujzgergely.ui.lyricsItem.LyricsItemActivity
import com.oe.nik.krujzgergely.ui.updatelyricsitem.UpdateLyricsItemActivity
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView
import kotlinx.android.synthetic.main.activity_lyricses.*


class LyricsActivity : AppCompatActivity(), IonLyricsClickEvent {

    private var doubleBackToExitPressedOnce = false

    private lateinit var lyricsViewModel : LyricsActivityViewModel

    private lateinit var allLyricsActivityAdapter : LyricsActivityAdapter
    private lateinit var favoriteLyricsActivityAdapter : LyricsActivityAdapter
    private lateinit var jazzLyricsActivityAdapter : LyricsActivityAdapter
    private lateinit var hiphopLyricsActivityAdapter : LyricsActivityAdapter
    private lateinit var rockLyricsActivityAdapter : LyricsActivityAdapter
    private lateinit var metalLyricsActivityAdapter : LyricsActivityAdapter
    private lateinit var punkLyricsActivityAdapter : LyricsActivityAdapter
    private lateinit var popLyricsActivityAdapter : LyricsActivityAdapter
    private lateinit var countryLyricsActivityAdapter : LyricsActivityAdapter
    private lateinit var operaLyricsActivityAdapter : LyricsActivityAdapter

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lyricses)

        setAdapters()
        setLyricsActivityViewModel()

        loadAllTypeOfRecycler()
    }

    private fun setAdapters()
    {
        allLyricsActivityAdapter = LyricsActivityAdapter(this, mutableListOf())
        favoriteLyricsActivityAdapter = LyricsActivityAdapter(this, mutableListOf())
        jazzLyricsActivityAdapter = LyricsActivityAdapter(this, mutableListOf())
        hiphopLyricsActivityAdapter = LyricsActivityAdapter(this, mutableListOf())
        rockLyricsActivityAdapter = LyricsActivityAdapter(this, mutableListOf())
        metalLyricsActivityAdapter = LyricsActivityAdapter(this, mutableListOf())
        punkLyricsActivityAdapter = LyricsActivityAdapter(this, mutableListOf())
        popLyricsActivityAdapter = LyricsActivityAdapter(this, mutableListOf())
        countryLyricsActivityAdapter = LyricsActivityAdapter(this, mutableListOf())
        operaLyricsActivityAdapter = LyricsActivityAdapter(this, mutableListOf())

    }

    private fun setLyricsActivityViewModel()
    {
        lyricsViewModel = ViewModelProvider(this).get(LyricsActivityViewModel::class.java)
    }

    private fun loadAllTypeOfRecycler()
    {
        for (type  in TypeOfTheRecycler.values())
        {
            loadLyricsMultiSnapRecyclerView(type)
        }
    }

    private fun loadLyricsMultiSnapRecyclerView(typeOfTheRecycler: TypeOfTheRecycler)
    {
        linearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        when(typeOfTheRecycler)
        {
            TypeOfTheRecycler.ALL      -> startLoadingTheLyrics(R.id.all_recycler_view,typeOfTheRecycler)
            TypeOfTheRecycler.FAVORITE -> startLoadingTheLyrics(R.id.favourite_recycler_view,typeOfTheRecycler)
            TypeOfTheRecycler.JAZZ     -> startLoadingTheLyrics(R.id.jazz_recycler_view,typeOfTheRecycler)
            TypeOfTheRecycler.HIPHOP   -> startLoadingTheLyrics(R.id.hiphop_recycler_view,typeOfTheRecycler)
            TypeOfTheRecycler.ROCK     -> startLoadingTheLyrics(R.id.rock_recycler_view,typeOfTheRecycler)
            TypeOfTheRecycler.METAL    -> startLoadingTheLyrics(R.id.metal_recycler_view,typeOfTheRecycler)
            TypeOfTheRecycler.PUNK     -> startLoadingTheLyrics(R.id.punk_recycler_view,typeOfTheRecycler)
            TypeOfTheRecycler.POP      -> startLoadingTheLyrics(R.id.pop_recycler_view,typeOfTheRecycler)
            TypeOfTheRecycler.COUNTRY  -> startLoadingTheLyrics(R.id.country_recycler_view,typeOfTheRecycler)
            TypeOfTheRecycler.OPERA    -> startLoadingTheLyrics(R.id.opera_recycler_view,typeOfTheRecycler)
        }
    }

    private fun startLoadingTheLyrics(recyclerId : Int, typeOfTheRecycler: TypeOfTheRecycler)
    {
        val lyricsRecyclerView = findViewById<MultiSnapRecyclerView>(recyclerId)

        when(typeOfTheRecycler)
        {
            TypeOfTheRecycler.ALL      -> setRecyclerViewComponents(lyricsRecyclerView,typeOfTheRecycler,allLyricsActivityAdapter)
            TypeOfTheRecycler.FAVORITE -> setRecyclerViewComponents(lyricsRecyclerView,typeOfTheRecycler,favoriteLyricsActivityAdapter)
            TypeOfTheRecycler.JAZZ     -> setRecyclerViewComponents(lyricsRecyclerView,typeOfTheRecycler,jazzLyricsActivityAdapter)
            TypeOfTheRecycler.HIPHOP   -> setRecyclerViewComponents(lyricsRecyclerView,typeOfTheRecycler,hiphopLyricsActivityAdapter)
            TypeOfTheRecycler.ROCK     -> setRecyclerViewComponents(lyricsRecyclerView,typeOfTheRecycler,rockLyricsActivityAdapter)
            TypeOfTheRecycler.METAL    -> setRecyclerViewComponents(lyricsRecyclerView,typeOfTheRecycler,metalLyricsActivityAdapter)
            TypeOfTheRecycler.PUNK     -> setRecyclerViewComponents(lyricsRecyclerView,typeOfTheRecycler,punkLyricsActivityAdapter)
            TypeOfTheRecycler.POP      -> setRecyclerViewComponents(lyricsRecyclerView,typeOfTheRecycler,popLyricsActivityAdapter)
            TypeOfTheRecycler.COUNTRY  -> setRecyclerViewComponents(lyricsRecyclerView,typeOfTheRecycler,countryLyricsActivityAdapter)
            TypeOfTheRecycler.OPERA    -> setRecyclerViewComponents(lyricsRecyclerView,typeOfTheRecycler,operaLyricsActivityAdapter)
        }

    }

    private fun setRecyclerViewComponents(currentRecyclerView: MultiSnapRecyclerView,
                                          typeOfTheRecycler: TypeOfTheRecycler,
                                          lyricsActivityAdapter : LyricsActivityAdapter)
    {
        currentRecyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = lyricsActivityAdapter }

        getDataFromTheViewModel(typeOfTheRecycler, lyricsActivityAdapter)
    }

    private fun getDataFromTheViewModel(typeOfTheRecycler : TypeOfTheRecycler,lyricsActivityAdapter : LyricsActivityAdapter)
    {
        val currentLyricsLiveData  : LiveData<List<LyricsModel>> = when(typeOfTheRecycler)
        {
            TypeOfTheRecycler.ALL      -> lyricsViewModel.getAllLyricsFromLocalDB()
            TypeOfTheRecycler.FAVORITE -> lyricsViewModel.getFavoriteLyricsFromLocalDB()
            TypeOfTheRecycler.JAZZ     -> lyricsViewModel.getJazzLyricsFromLocalDB()
            TypeOfTheRecycler.HIPHOP   -> lyricsViewModel.getHipHopLyricsFromLocalDB()
            TypeOfTheRecycler.ROCK     -> lyricsViewModel.getRockLyricsFromLocalDB()
            TypeOfTheRecycler.METAL    -> lyricsViewModel.getMetalLyricsFromLocalDB()
            TypeOfTheRecycler.PUNK     -> lyricsViewModel.getPunkLyricsFromLocalDB()
            TypeOfTheRecycler.POP      -> lyricsViewModel.getPopLyricsFromLocalDB()
            TypeOfTheRecycler.COUNTRY  -> lyricsViewModel.getCountryLyricsFromLocalDB()
            TypeOfTheRecycler.OPERA    -> lyricsViewModel.getOperaLyricsFromLocalDB()
        }
        observeLyricsDataToUi(currentLyricsLiveData,lyricsActivityAdapter)
    }

    private fun observeLyricsDataToUi(lyricsLiveData : LiveData<List<LyricsModel>>, adapter: LyricsActivityAdapter)
    {
        lyricsLiveData.observe(this, Observer {lyricsList -> adapter.swapData(lyricsList) })
    }

    @SuppressLint("RestrictedApi")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.lyrics_activity_menu_options, menu)

        if (menu is MenuBuilder)
        {
            if (checkIfTheCurrentSDKIsGreaterOrEqualsWithAndroidQ())
            {
                menu.setOptionalIconsVisible(true)
            }
        }

        val pictureUrl : Uri? = setPictureUrlByTheLoggedInProfile()

        setProfilePictureIntoOptionMenuItemIcon(menu, pictureUrl)

        return true
    }

    private fun checkIfTheCurrentSDKIsGreaterOrEqualsWithAndroidQ() : Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

    private fun setPictureUrlByTheLoggedInProfile() : Uri?
    {
        return when(GoogleLogin.googleAccount)
        {
            null -> Uri.parse(SpotifyLogin.spotifyAccount!!.AvatarURL)
            else -> GoogleLogin.googleAccount!!.photoUrl
        }
    }

    private fun setProfilePictureIntoOptionMenuItemIcon(menu: Menu?, photoUrl : Uri?)
    {
        val profilePictureMenuItem = menu!!.findItem(R.id.ProfilePicture)

        Glide.with(this).asBitmap().load(photoUrl).into(object : SimpleTarget<Bitmap?>(100,100)
        {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap?>?)
            {
                profilePictureMenuItem.icon = BitmapDrawable(resources, resource)
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId)
        {
            R.id.CreateNewLyrics ->  startCreateLyricsActivity()
            R.id.SearchField ->      searchBetweenAllLyrics(item)
            R.id.ProfilePicture ->   startAccountInfoActivity()
            else -> super.onOptionsItemSelected(item)
        }

    private fun startCreateLyricsActivity() : Boolean
    {
        startActivity(Intent(this,CreateLyricsActivity::class.java))
        return true
    }

    private fun startAccountInfoActivity() : Boolean
    {
        startActivity(Intent(this,AccountInfoActivity::class.java))
        overridePendingTransition( R.xml.slide_in_up, R.xml.slide_out_up )
        return true
    }

    private fun searchBetweenAllLyrics(item: MenuItem) : Boolean
    {
        val searchView= setSearchView(item)
        scrollToTheTopSmoothly()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener
        {
            override fun onQueryTextSubmit(query: String?): Boolean { return true }
            override fun onQueryTextChange(searchText: String?): Boolean
            {
                searchIfTheSearchFieldIsNotEmpty(searchText!!)
                return true
            }
        })

        return true
    }

    private fun setSearchView(item: MenuItem) : SearchView
    {
        val searchView = item.actionView as SearchView

        searchView.apply{
            isIconifiedByDefault = false
            onActionViewExpanded()
            queryHint = "Search lyrics"
        }
        return searchView
    }

    private fun scrollToTheTopSmoothly()
    {
        val scrollView =  lyricsScrollView
        scrollView.smoothScrollTo(0,0)
    }

    private fun searchIfTheSearchFieldIsNotEmpty(searchText : String)
    {
        when(searchText.isNotEmpty())
        {
            true  -> allLyricsActivityAdapter.getSearchedDataFromTheList(searchText)
            false -> allLyricsActivityAdapter.noItemFoundInLyricsListWhenSearched()
        }
    }

    override fun onBackPressed()
    {
        when(doubleBackToExitPressedOnce)
        {
            true -> exitTheApp()
            false -> delayTimeBetweenTwoBackButtonPress()
        }
    }

    private fun exitTheApp()
    {
        GoogleLogin.googleAccount = null
        finishAffinity()
    }

    private fun delayTimeBetweenTwoBackButtonPress()
    {
        this.doubleBackToExitPressedOnce = true
        showMessageBoxWhenPressingBackTwice()
        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

    private fun showMessageBoxWhenPressingBackTwice() { Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show() }

    override fun onLyricsSelected()
    {
        startActivity( Intent(this, LyricsItemActivity::class.java))
        overridePendingTransition( R.xml.slide_in_up, R.xml.slide_out_up )
    }
    override fun onUpdateOptionsClicked()
    {
        startActivity( Intent(this, UpdateLyricsItemActivity::class.java))
        overridePendingTransition( R.xml.slide_in_up, R.xml.slide_out_up )
    }
    override fun onDeleteOptionsClicked(lyricsModel: LyricsModel) { lyricsViewModel.deleteLyricsFromLocalDB(lyricsModel)   }
}