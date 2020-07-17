package com.oe.nik.krujzgergely.controllers

import android.app.Application
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.oe.nik.krujzgergely.R
import com.oe.nik.krujzgergely.ui.main.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URI
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class GoogleLogin(var application: Application)
{
    val GOOGLE_REQUEST_CODE = 123
    var googleSignInOptions : GoogleSignInOptions
    var mGoogleSignInClient : GoogleSignInClient
    companion object
    {
        lateinit var googleAccount : GoogleSignInAccount
    }

    init {

        var token = "820173271126-5euu594fvgojr9iuefu3qtstbb16tjqu.apps.googleusercontent.com"
        googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(token)
            .build()
        this.mGoogleSignInClient = GoogleSignIn.getClient(application,googleSignInOptions)
    }

    fun startGoogleLogin(data: Intent?)
    {
        val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
        handleSignInResult(task)
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try
        {
            googleAccount = completedTask.getResult(ApiException::class.java)!!
        }
        catch (e: ApiException) { }
    }
}