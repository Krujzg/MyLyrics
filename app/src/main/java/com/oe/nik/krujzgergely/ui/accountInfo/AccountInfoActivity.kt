package com.oe.nik.krujzgergely.ui.accountInfo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.oe.nik.krujzgergely.R
import com.oe.nik.krujzgergely.controllers.logincontroller.GoogleLogin
import com.oe.nik.krujzgergely.controllers.logincontroller.SpotifyLogin
import com.oe.nik.krujzgergely.databinding.ActivityAccountinfoBinding
import com.oe.nik.krujzgergely.services.SpotifyService
import com.oe.nik.krujzgergely.ui.lyrics.LyricsActivity
import com.oe.nik.krujzgergely.util.CustomLogoutProgressDialog
import com.oe.nik.krujzgergely.util.testutil.EspressoIdlingResource
import kotlinx.android.synthetic.main.activity_accountinfo.*

class AccountInfoActivity : AppCompatActivity()
{
    private val progressDialog = CustomLogoutProgressDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accountinfo)

        val accountInfoActivityViewModel = ViewModelProvider(this).get(AccountInfoActivityViewModel::class.java)

        DataBindingUtil.setContentView<ActivityAccountinfoBinding>(this,R.layout.activity_accountinfo).apply {
            this.lifecycleOwner = this@AccountInfoActivity
            this.accountInfoModel = accountInfoActivityViewModel
        }

        val photoUrl = getPhotoUrlBasedOnLogin()

        setBigProfilePicture(photoUrl!!)

        setLogoutButtonOnClickListener()
        EspressoIdlingResource.decrement()
    }

    private fun getPhotoUrlBasedOnLogin() : Uri? = when (GoogleLogin.googleAccount)
    {
        null -> Uri.parse(SpotifyLogin.spotifyAccount!!.AvatarURL)
        else -> GoogleLogin.googleAccount!!.photoUrl
    }

    private fun setBigProfilePicture(photoUrl : Uri?) { Glide.with(this).load(photoUrl).into(BigProfilePic) }

    private fun setLogoutButtonOnClickListener()
    {
        LogoutButton.setOnClickListener {

            progressDialog.showCustomLogoutProgressDialog(this,"Logging out...")
            delayLogoutBy1500MilliSeconds()
        }
    }

    private fun delayLogoutBy1500MilliSeconds()
    {
        Handler().postDelayed({ logoutFromCurrentAccount() }, 1500)
    }

    private fun logoutFromCurrentAccount()
    {
        when(GoogleLogin.googleAccount)
        {
            null -> signOutFromSpotify()
            else -> signOutFromGoogle()
        }
    }

    private fun signOutFromGoogle()
    {
        GoogleLogin.mGoogleSignInClient!!.signOut().addOnCompleteListener(this, OnCompleteListener
        {
            GoogleLogin.googleAccount = null
            finishAffinity()
        })
    }

    private fun signOutFromSpotify()
    {
        SpotifyService.disconnect()
        finishAffinity()
    }

    override fun onBackPressed()
    {
        startActivity( Intent(this, LyricsActivity::class.java))
        overridePendingTransition( R.xml.slide_in_down, R.xml.slide_out_down )
    }
}