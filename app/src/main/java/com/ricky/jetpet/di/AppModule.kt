package com.ricky.jetpet.di

import android.content.Context
import com.ricky.jetpet.utils.Constants.BASE_URL
import com.ricky.jetpet.data.local.DataStoreUtil
import com.ricky.jetpet.data.network.retrofit.PetFinderApiService
import com.ricky.jetpet.data.network.token.AccessTokenRepository
import com.ricky.jetpet.data.network.token.AccessTokenRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDataStoreUtil(@ApplicationContext context: Context): DataStoreUtil {
        return DataStoreUtil(context)
    }

    @Singleton
    @Provides
    fun providePetFinderApiService(): PetFinderApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PetFinderApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAccessTokenRepository(
        api: PetFinderApiService,
        dataStoreUtil: DataStoreUtil
    ): AccessTokenRepository {
        return AccessTokenRepositoryImpl(dataStore = dataStoreUtil, api = api)
    }
}