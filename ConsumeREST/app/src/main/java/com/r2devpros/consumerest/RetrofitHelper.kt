package com.r2devpros.consumerest

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {
    private val logLevel =
        if (!BuildConfig.DEBUG) HttpLoggingInterceptor.Level.NONE
        else HttpLoggingInterceptor.Level.BODY

    fun createOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = logLevel })
        .readTimeout(120, TimeUnit.SECONDS)
        .build()

    inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T =
        Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(T::class.java)
}