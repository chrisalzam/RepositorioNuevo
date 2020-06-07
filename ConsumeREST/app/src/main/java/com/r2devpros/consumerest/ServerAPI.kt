package com.r2devpros.consumerest

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ServerAPI {
    @GET("v2/5edc521431000080006b21af")
    fun getCourseAsync(): Deferred<CourseDetailsResponse>

    @GET("v2/5d262d342f00003b9dc10dc1")
    fun getOtherAsync(): Deferred<List<OtherResponse>>
}