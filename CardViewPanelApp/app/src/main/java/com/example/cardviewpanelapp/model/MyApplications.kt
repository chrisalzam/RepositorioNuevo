package com.example.cardviewpanelapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MyApplications(
    var appId: String = "",
    var name: String = "",
    var deepLink: String = "",
    var iconUrl: String = "",
    var iconResourceName: String = "",
    var adminApp: Boolean = true
): Parcelable