package com.r2devpros.consumerest


import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class OtherResponse(
    @Json(name = "content") val content: String,
    @Json(name = "backgroundUrl") val backgroundUrl: String,
    @Json(name = "type") val type: String
)
