package com.r2devpros.myrestapptest.repository.remote.response

import com.squareup.moshi.Json

data class StoresByZipCodeResponse(
    @Json(name = "Stores") val Stores: List<Store>
) {
    data class Store(
        @Json(name = "StoreID") val StoreID: String,
        @Json(name = "Phone") val Phone: String,
        @Json(name = "AddressDescription") val AddressDescription: String,
        @Json(name = "HoursDescription") val HoursDescription: String,
        @Json(name = "ServiceHoursDescription") val ServiceHoursDescription: ServiceHoursDescriptionClass,
        @Json(name = "StoreCoordinates") val StoreCoordinates: StoreCoordinatesClass
    ) {
        data class ServiceHoursDescriptionClass(
            @Json(name = "Carryout") val Carryout: String,
            @Json(name = "Delivery") val Delivery: String,
            @Json(name = "DriveUpCarryout") val DriveUpCarryout: String
        )

        data class StoreCoordinatesClass(
            @Json(name = "StoreLatitude") val StoreLatitude: Double,
            @Json(name = "StoreLongitude") val StoreLongitude: Double
        )
    }
}