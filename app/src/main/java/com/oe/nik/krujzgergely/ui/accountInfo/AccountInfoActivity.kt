package com.oe.nik.krujzgergely.ui.accountInfo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.oe.nik.krujzgergely.R
import com.oe.nik.krujzgergely.controllers.logincontroller.GoogleLogin
import com.oe.nik.krujzgergely.controllers.logincontroller.SpotifyLogin
import com.oe.nik.krujzgergely.databinding.ActivityAccountinfoBinding
import com.oe.nik.krujzgergely.services.SpotifyService
import com.oe.nik.krujzgergely.ui.lyrics.LyricsActivity
import com.oe.nik.krujzgergely.ui.lyricsItem.LyricsItemActivity
import com.oe.nik.krujzgergely.util.CustomLogoutProgressDialog
import com.spotify.sdk.android.authentication.AuthenticationHandler
import kotlinx.android.synthetic.main.activity_accountinfo.*
import kotlin.system.exitProcess

class AccountInfoActivity : AppCompatActivity()
{
    private val progressDialog = CustomLogoutProgressDialog()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accountinfo)

        val accountInfoActivityViewModel = ViewModelProvider(this).get(AccountInfoActivityViewModel::class.java)

        DataBindingUtil.setContentView<ActivityAccountinfoBinding>(this,R.layout.activity_accountinfo).apply {
            this.lifecycleOwner = this@AccountInfoActivity
            this.accountInfoModel = accountInfoActivityViewModel
        }

        val photoUrl : Uri? = when (GoogleLogin.googleAccount) {
            null -> Uri.parse(SpotifyLogin.spotifyAccount!!.AvatarURL)
            else -> GoogleLogin.googleAccount!!.photoUrl
        }

        setBigProfilePicture(photoUrl!!)

        LogoutButton.setOnClickListener {

            progressDialog.show(this,"Logging out...")
            val handler = Handler()
            handler.postDelayed({ logoutFromCurrentAccount() }, 1500) }
    }

    private fun logoutFromCurrentAccount()
    {
        val googleAccount = GoogleLogin.googleAccount
        when(googleAccount)
        {
            null -> signOutFromSpotify()
            else -> signOutFromGoogle()
        }
    }

    private fun signOutFromGoogle()
    {
        GoogleLogin.mGoogleSignInClient!!.signOut().addOnCompleteListener(this, OnCompleteListener {

            GoogleLogin.googleAccount = null
            finishAffinity()
        })
    }

    private fun signOutFromSpotify()
    {
        SpotifyService.disconnect()
        finishAffinity()
    }

    private fun setBigProfilePicture(photoUrl : Uri?) { Glide.with(this).load(photoUrl).into(BigProfilePic) }

    override fun onBackPressed() {
        startActivity( Intent(this, LyricsActivity::class.java))
        overridePendingTransition( R.xml.slide_in_down, R.xml.slide_out_down )
    }
}