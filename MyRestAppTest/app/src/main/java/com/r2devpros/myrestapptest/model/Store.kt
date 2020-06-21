package com.r2devpros.myrestapptest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Store(
    var id: String,
    var phone: String,
    var address: String,
    var serviceHoursDescription: String
):Parcelable