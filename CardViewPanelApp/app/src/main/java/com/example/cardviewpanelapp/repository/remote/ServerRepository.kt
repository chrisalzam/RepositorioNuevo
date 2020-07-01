package com.example.cardviewpanelapp.repository.remote

import com.example.cardviewpanelapp.model.MyApplications
import com.example.cardviewpanelapp.repository.ApiResult
import kotlinx.coroutines.Deferred

interface ServerRepository {
    fun getMyApplicationsAsync(
        appId: String
    ): Deferred<ApiResult<List<MyApplications>>>
}