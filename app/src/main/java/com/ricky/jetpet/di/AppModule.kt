package com.ricky.jetpet.di

import android.content.Context
import com.ricky.jetpet.data.local.DataStoreUtil
import com.ricky.jetpet.data.network.interceptors.AccessInterceptor
import com.ricky.jetpet.data.network.interceptors.AccessTokenAuthorization
import com.ricky.jetpet.data.network.mappers.AnimalApiMapper
import com.ricky.jetpet.data.network.mappers.AnimalApiMapperImpl
import com.ricky.jetpet.data.network.mappers.PetApiMapper
import com.ricky.jetpet.data.network.mappers.PetApiMapperImpl
import com.ricky.jetpet.data.network.models.Animal
import com.ricky.jetpet.data.network.models.ApiAnimals
import com.ricky.jetpet.data.network.retrofit.PetFinderApiService
import com.ricky.jetpet.data.network.token.AccessTokenRepository
import com.ricky.jetpet.data.network.token.AccessTokenRepositoryImpl
import com.ricky.jetpet.data.repository.PetRepositoryImpl
import com.ricky.jetpet.domain.model.Pet
import com.ricky.jetpet.domain.repository.PetRepository
import com.ricky.jetpet.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
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
    fun provideOkHttpClient(tokenRepository: AccessTokenRepository): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AccessInterceptor(tokenRepository))
            .authenticator(AccessTokenAuthorization(tokenRepository))
            .build()
    }

    @Singleton
    @Provides
    fun providePetFinderApiService(okHttpClient: OkHttpClient): PetFinderApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
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

    @Provides
    @Singleton
    fun providePetApiMapper(): PetApiMapper<List<Pet>, ApiAnimals> {
        return PetApiMapperImpl()
    }

    @Provides
    @Singleton
    fun provideAnimalApiMapper(): AnimalApiMapper<Pet, Animal> {
        return AnimalApiMapperImpl()
    }

    @Provides
    @Singleton
    fun providePetRepository(
        api: PetFinderApiService,
        petApiMapper: PetApiMapper<List<Pet>, ApiAnimals>,
        animalMapper: AnimalApiMapper<Pet, Animal>
    ): PetRepository {
        return PetRepositoryImpl(
            apiService = api,
            petMapper = petApiMapper,
            animalMapper = animalMapper
        )
    }
}