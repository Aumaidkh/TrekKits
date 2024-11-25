package com.hopcape.core.di

import com.hopcape.core.data.SessionStorage
import com.hopcape.core.data.SharedPreferencesStorage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class CoreModuleBindings {

    @Binds
    internal abstract fun bindSessionStorage(impl: SharedPreferencesStorage): SessionStorage
}