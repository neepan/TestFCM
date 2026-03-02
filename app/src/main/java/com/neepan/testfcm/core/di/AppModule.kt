package com.neepan.testfcm.core.di

import com.neepan.testfcm.BuildConfig
import com.neepan.testfcm.auth.data.repository.AuthRepositoryImpl
import com.neepan.testfcm.auth.domain.repository.AuthRepository
import com.neepan.testfcm.auth.domain.usecase.LoginWithUsernameUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import javax.inject.Singleton

// Teaching the Kitchen(Hilt) How to Cook(build)

@Module
@InstallIn(SingletonComponent::class) //this means these dependencies  live as long as the app lives

object AppModule{

    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient{
        return createSupabaseClient(
            supabaseUrl = BuildConfig.SUPABASE_URL,
            supabaseKey = BuildConfig.SUPABASE_KEY
        ){
            install(Auth)
            install(Postgrest)
            install(Realtime)
        }
    }


    @Provides
    @Singleton
    fun provideAuthRepository(supabaseClient: SupabaseClient): AuthRepository{
        // Hilt will automatically grab the SupabaseClient we built above and pass it in here!
        return AuthRepositoryImpl(supabaseClient)
    }


    @Provides
    @Singleton
    fun provideLoginUseCase(repository: AuthRepository): LoginWithUsernameUseCase{
        return LoginWithUsernameUseCase(repository)
    }
}