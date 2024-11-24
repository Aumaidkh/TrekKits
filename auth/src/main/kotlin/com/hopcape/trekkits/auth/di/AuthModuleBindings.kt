package com.hopcape.trekkits.auth.di

import android.content.Context
import com.hopcape.trekkits.auth.data.api.AuthService
import com.hopcape.trekkits.auth.data.api.FirebaseAuthService
import com.hopcape.trekkits.auth.data.api.GoogleSignInService
import com.hopcape.trekkits.auth.data.repository.AuthRepository
import com.hopcape.trekkits.auth.data.repository.AuthRepositoryImpl
import com.hopcape.trekkits.auth.domain.usecase.ForgotPasswordUseCase
import com.hopcape.trekkits.auth.domain.usecase.LoginUseCase
import com.hopcape.trekkits.auth.domain.usecase.RegisterUseCase
import com.hopcape.trekkits.auth.domain.validation.EmailValidator
import com.hopcape.trekkits.auth.domain.validation.FullNameValidator
import com.hopcape.trekkits.auth.domain.validation.PasswordValidator
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class AuthModuleBindings{

    @Binds
    internal abstract fun bindsAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    internal abstract fun bindsAuthService(impl: FirebaseAuthService): AuthService

    companion object {

        @Provides
        internal fun providesLoginUseCase(
            repository: AuthRepository
        ): LoginUseCase{
            return LoginUseCase(
                repository = repository
            )
        }

        @Provides
        internal fun providesRegisterUseCase(
            repository: AuthRepository
        ): RegisterUseCase {
            return RegisterUseCase(
                authRepository = repository
            )
        }

        @Provides
        internal fun providesForgotPasswordUseCase(
            repository: AuthRepository
        ): ForgotPasswordUseCase {
            return ForgotPasswordUseCase(
                repository = repository
            )
        }

        @Provides
        internal fun providesEmailValidatorUseCase(): EmailValidator {
            return EmailValidator()
        }

        @Provides
        internal fun providesPasswordValidatorUseCase(): PasswordValidator {
            return PasswordValidator()
        }

        @Provides
        internal fun providesFullNameValidatorUseCase(): FullNameValidator {
            return FullNameValidator()
        }

    }
}