package com.example.cardviewpanelapp.repository.remote

import com.example.cardviewpanelapp.model.Application
import com.example.cardviewpanelapp.repository.ApiResult
import kotlinx.coroutines.Deferred

interface ServerRepository {
    fun getApplicationsAsync(
    ): Deferred<ApiResult<List<Application>>>
}