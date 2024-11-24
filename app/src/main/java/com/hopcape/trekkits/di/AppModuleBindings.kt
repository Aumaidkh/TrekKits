package com.hopcape.trekkits.di

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModuleBindings {

    @Provides
    @Singleton
    fun providesFirebaseAuth(
        @ApplicationContext context: Context
    ): FirebaseAuth {
        FirebaseApp.initializeApp(context)
        return FirebaseAuth.getInstance()
    }
}