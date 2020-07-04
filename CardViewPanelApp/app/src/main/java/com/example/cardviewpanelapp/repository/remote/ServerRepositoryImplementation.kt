package com.example.cardviewpanelapp.repository.remote

import com.example.cardviewpanelapp.model.Application
import com.example.cardviewpanelapp.repository.ApiResult
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import timber.log.Timber

class ServerRepositoryImplementation(
    private val serverAPI: ServerAPI
) : ServerRepository {
    override fun getApplicationsAsync(): Deferred<ApiResult<List<Application>>> = GlobalScope.async {
            try {
                ApiResult.Ok(
                    serverAPI.getApplicationsAsync(
                    ).await().map { responseApplications ->
                        Application(
                            appId = responseApplications.appId,
                            name = responseApplications.name,
                            deepLink = responseApplications.deepLink,
                            iconUrl = responseApplications.iconUrl,
                            iconResourceName = responseApplications.iconResourceName,
                            adminApp = responseApplications.adminApp
                        )
                    }
                )
            } catch (e: Exception) {
                Timber.d("ServerRepositoryImplementation_TAG: getDefinitionsAsync: Exception: ${e}")
                ApiResult.Error(e)
            }
        }

}