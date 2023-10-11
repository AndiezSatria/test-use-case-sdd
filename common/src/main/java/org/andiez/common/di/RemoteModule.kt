package org.andiez.common.di

import com.github.ajalt.timberkt.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.andiez.common.data.source.remote.interceptor.HeaderInterceptor
import org.andiez.common.data.source.remote.service.AppService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by AndiezSatria on 17/04/2023.
 */

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    fun provideHeaderInterceptor(): HeaderInterceptor {
        return HeaderInterceptor()
    }

    @Provides
    fun provideOkHttpClient(headerInterceptor: HeaderInterceptor): OkHttpClient =
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(headerInterceptor)
                .build()
        } else {
            OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .build()
        }

    @Provides
    fun provideApiService(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @Provides
    fun provideApiServices(retrofit: Retrofit): AppService =
        retrofit.create(AppService::class.java)
}