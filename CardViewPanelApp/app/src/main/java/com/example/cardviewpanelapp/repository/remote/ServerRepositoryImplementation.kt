package com.example.cardviewpanelapp.repository.remote

import com.example.cardviewpanelapp.model.MyApplications
import com.example.cardviewpanelapp.repository.ApiResult
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import timber.log.Timber

class ServerRepositoryImplementation(
    private val serverAPI: ServerAPI
) : ServerRepository {
    override fun getMyApplicationsAsync(appId: String): Deferred<ApiResult<List<MyApplications>>> =
        GlobalScope.async {
            try {
                ApiResult.Ok(
                    serverAPI.getMyApplicationsAsync(appId)
                        .await().Applications.map { responseApplications ->
                        MyApplications(
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
                Timber.d("ServerRepositoryImplementation_TAG: getDefinitionsAsync: Exception: $e")
                ApiResult.Error(e)
            }
        }

}