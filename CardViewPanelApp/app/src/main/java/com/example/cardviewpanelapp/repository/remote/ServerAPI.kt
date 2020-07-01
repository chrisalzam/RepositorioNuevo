package com.example.cardviewpanelapp.repository.remote

import com.example.cardviewpanelapp.repository.remote.response.MyApplicationsResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ServerAPI {
    @GET("v3/5bc962c9-ff64-44b5-a679-b98676ce1f1d")
    fun getMyApplicationsAsync(
        @Query(value = "appId") appId: String
    ) : Deferred<MyApplicationsResponse>
}