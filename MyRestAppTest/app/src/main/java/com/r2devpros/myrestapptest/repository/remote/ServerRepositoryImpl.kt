package com.r2devpros.myrestapptest.repository.remote

import com.r2devpros.myrestapptest.model.Store
import com.r2devpros.myrestapptest.repository.ApiResult
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import timber.log.Timber

class ServerRepositoryImpl(
    private val serverAPI: ServerAPI
): ServerRepository {
    override fun getStoresByAddressAsync(address: String): Deferred<ApiResult<List<Store>>> = GlobalScope.async{
        try {
            ApiResult.Ok(
                serverAPI.getStoresByAddressAsync(address).await().Stores.map { responseStore ->
                    Store(
                        id = responseStore.StoreID,
                        phone = responseStore.Phone,
                        address = responseStore.AddressDescription,
                        serviceHoursDescription = responseStore.ServiceHoursDescription.let { shd -> "carryout: ${shd.Carryout}, delivery: ${shd.Delivery}" }
                    )
                }
            )
        } catch (e: Exception) {
            Timber.d("ServerRepositoryImpl_TAG: getStoresByAddressAsync: exception: $e")
            ApiResult.Error(e)
        }
    }
}