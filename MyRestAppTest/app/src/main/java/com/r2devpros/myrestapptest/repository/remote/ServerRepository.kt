package com.r2devpros.myrestapptest.repository.remote

import com.r2devpros.myrestapptest.model.Store
import com.r2devpros.myrestapptest.repository.ApiResult
import kotlinx.coroutines.Deferred

interface ServerRepository {
    fun getStoresByAddressAsync(
        address: String
    ): Deferred<ApiResult<List<Store>>>
}