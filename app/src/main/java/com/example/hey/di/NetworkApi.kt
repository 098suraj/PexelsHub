package com.example.hey.di

import com.example.data.api.ImvApi
import com.example.hey.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkApi {

    @Provides
    @Singleton
    fun provideHttpClient():OkHttpClient{
        return  OkHttpClient.Builder()
            .readTimeout(60,TimeUnit.SECONDS)
            .connectTimeout(60,TimeUnit.SECONDS)
            .build()
   
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }


    @Provides
    @Singleton
    fun provideIMVApi(retrofit: Retrofit):ImvApi=
         retrofit.create(ImvApi::class.java)
}