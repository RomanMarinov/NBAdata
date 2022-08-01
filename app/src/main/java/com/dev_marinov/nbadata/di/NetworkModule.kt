package com.dev_marinov.nbadata.di

import com.dev_marinov.nbadata.data.remote.dto.NbaService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideNbaService(retrofit: Retrofit): NbaService {
        return retrofit.create(NbaService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
//        val baseUrl = "https://free-nba.p.rapidapi.com/games?page=0&per_page=25"
        val baseUrl = "https://free-nba.p.rapidapi.com/"
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(Interceptor { chain ->
                val builder = chain.request().newBuilder()
                builder.addHeader("X-RapidAPI-Host", "free-nba.p.rapidapi.com")
                builder.addHeader(
                    "X-RapidAPI-Key",
                    "3f235a9f12msh754db7d5868e472p168e1djsn3c41eccf8098"
                )
                return@Interceptor chain.proceed(builder.build())
            })
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }

}