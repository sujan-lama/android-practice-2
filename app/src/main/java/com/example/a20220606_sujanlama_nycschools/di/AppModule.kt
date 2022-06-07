package com.example.a20220606_sujanlama_nycschools.di

import android.app.Application
import android.content.Context
import androidx.viewbinding.BuildConfig
import com.example.a20220606_sujanlama_nycschools.api.ApiHelper
import com.example.a20220606_sujanlama_nycschools.api.ApiHelperImpl
import com.example.a20220606_sujanlama_nycschools.api.ApiService
import com.example.a20220606_sujanlama_nycschools.constant.API
import com.example.a20220606_sujanlama_nycschools.repository.Repository
import com.example.a20220606_sujanlama_nycschools.repository.SchoolRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesContext(app: Application): Context = app.applicationContext

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        if (BuildConfig.DEBUG) {
            val logger = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
            return OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
        }

        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API.BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun providesApiHelper(apiHelperImpl: ApiHelperImpl): ApiHelper = apiHelperImpl

    @Provides
    @Singleton
    fun providesBaseRepository(schoolRepository: SchoolRepository): Repository =
        schoolRepository
}