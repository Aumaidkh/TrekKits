package com.hopcape.trekkits.auth.data.api

import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.hopcape.common.domain.wrappers.Result
import com.hopcape.trekkits.auth.AuthError
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "GoogleSignInService"
private const val WEB_CLIENT_ID = "1059617624483-8c9llmo8th01nmijvh5ovhpbp2qr7au1.apps.googleusercontent.com"

@Singleton
class GoogleSignInService @Inject constructor() {

    private lateinit var context: Context

    private val credentialManager by lazy {
        CredentialManager.create(context)
    }

    private val googleIdOption by lazy {
        GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(WEB_CLIENT_ID)
            .setNonce(null)
            .build()
    }

    private val request by lazy {
        GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
    }

    fun register(context: Context){
        this.context = context
    }

    suspend fun launchClient(): Result<String,AuthError>{
        try {
            val result = credentialManager
                .getCredential(
                    request = request,
                    context = context
                )

            val credentials = result.credential

            val googleIdTokenCredential = GoogleIdTokenCredential
                .createFrom(credentials.data)

            val googleIdToken = googleIdTokenCredential.idToken
            return Result.Success(googleIdToken)
        } catch (e: Exception){
            Log.d(
                TAG,
                "launchClient: ${e.message}"
            )
            return Result.Error(AuthError.Remote.SOMETHING_WENT_WRONG)
        }
    }
}