package com.r2devpros.myrestapptest.repository.remote

import com.r2devpros.myrestapptest.repository.remote.response.StoresByZipCodeResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ServerAPI {
    @GET("power/store-locator")
    fun getStoresByAddressAsync(
        @Query("s") address: String
    ) : Deferred<StoresByZipCodeResponse>
}