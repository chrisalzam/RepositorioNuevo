package com.r2devpros.myrestapptest.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.r2devpros.myrestapptest.BuildConfig
import com.r2devpros.myrestapptest.repository.remote.ServerAPI
import com.r2devpros.myrestapptest.repository.remote.ServerRepository
import com.r2devpros.myrestapptest.repository.remote.ServerRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {
    single { createOkHttpClient() }
}

fun createOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply { level = logLevel })
//    .addInterceptor(HttpLog())
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


private val logLevel =
    if (!BuildConfig.DEBUG) HttpLoggingInterceptor.Level.NONE
    else HttpLoggingInterceptor.Level.BODY

val serverServiceModule = module {
    single<ServerAPI> { createWebService(get(), "https://order.dominos.com/") }
    single<ServerRepository> { ServerRepositoryImpl(get()) }
}

val repositoryModule = listOf(
    apiModule,
    serverServiceModule
)