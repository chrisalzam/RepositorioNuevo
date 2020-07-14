package com.example.cardviewpanelapp.di

import com.example.cardviewpanelapp.BuildConfig
import com.example.cardviewpanelapp.repository.remote.ServerRepositoryImplementation
import com.example.cardviewpanelapp.repository.remote.ServerAPI
import com.example.cardviewpanelapp.repository.remote.ServerRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
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
    single<ServerAPI> { createWebService(get(), "https://run.mocky.io/") }
    single<ServerRepository> {
        ServerRepositoryImplementation(
            get()
        )
    }
}

val repositoryModule = listOf(
    apiModule,
    serverServiceModule
)