package com.hopcape.trekkits.auth.di

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

@Module
@InstallIn(ViewModelComponent::class)
abstract class AuthModuleBindings{

    @Binds
    internal abstract fun bindsAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    companion object {
        @Provides
        internal fun providesLoginUseCase(
            emailValidator: EmailValidator,
            passwordValidator: PasswordValidator,
            repository: AuthRepository
        ): LoginUseCase{
            return LoginUseCase(
                emailValidator = emailValidator,
                passwordValidator = passwordValidator,
                repository = repository
            )
        }

        @Provides
        internal fun providesRegisterUseCase(
            emailValidator: EmailValidator,
            fullNameValidator: FullNameValidator,
            passwordValidator: PasswordValidator,
            repository: AuthRepository
        ): RegisterUseCase {
            return RegisterUseCase(
                emailValidator = emailValidator,
                passwordValidator = passwordValidator,
                fullNameValidator = fullNameValidator,
                authRepository = repository
            )
        }

        @Provides
        internal fun providesForgotPasswordUseCase(
            emailValidator: EmailValidator,
            repository: AuthRepository
        ): ForgotPasswordUseCase {
            return ForgotPasswordUseCase(
                emailValidator = emailValidator,
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