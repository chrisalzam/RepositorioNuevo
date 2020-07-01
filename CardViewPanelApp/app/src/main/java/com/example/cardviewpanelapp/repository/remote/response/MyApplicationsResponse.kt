package com.example.cardviewpanelapp.repository.remote.response

import com.squareup.moshi.Json

data class MyApplicationsResponse(
    @Json(name = "Applications") val Applications: List<MyApplications>
) {
    data class MyApplications(
        @Json(name = "appId") val appId: String,
        @Json(name = "name") val name: String,
        @Json(name = "deepLink") val deepLink: String,
        @Json(name = "iconUrl") val iconUrl: String,
        @Json(name = "iconResourceName") val iconResourceName: String,
        @Json(name = "adminApp") val adminApp: Boolean
    )
}