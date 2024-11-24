package com.hopcape.trekkits.auth.data.api

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.hopcape.common.domain.wrappers.Result
import com.hopcape.trekkits.auth.data.AuthDataError
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class FirebaseAuthService @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): AuthService {

    override suspend fun login(
        email: String,
        password: String
    ): Result<Unit, AuthDataError> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email,password)
                .await()
            result.user?.let { user ->
                if (user.isEmailVerified) {
                    Result.Success(Unit)
                } else {
                    Result.Error(AuthDataError.EMAIL_NOT_VERIFIED)
                }
            } ?: run {
                Result.Error(AuthDataError.USER_NOT_FOUND)
            }
        }catch (e: Exception){
            Result.Error(AuthDataError.SOMETHING_WENT_WRONG)
        }
    }

    override suspend fun register(
        email: String,
        password: String,
        name: String
    ): Result<Unit, AuthDataError> {
        return try {
            // Create user with email and password
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password)
                .await()

            // Update display name
            result.user?.let { user ->
                val profileUpdateRequest = UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .build()
                user.updateProfile(profileUpdateRequest).await()

                // Send email verification
                user.sendEmailVerification().await()
                Result.Success(Unit)
            } ?: run {
                Result.Error(AuthDataError.USER_NOT_FOUND)
            }
        } catch (e: Exception) {
            Result.Error(AuthDataError.SOMETHING_WENT_WRONG)
        }
    }

    override suspend fun forgotPassword(email: String): Result<Unit, AuthDataError> {
        return try {
            firebaseAuth.sendPasswordResetEmail(email)
                .await()
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(AuthDataError.SOMETHING_WENT_WRONG)
        }
    }
}