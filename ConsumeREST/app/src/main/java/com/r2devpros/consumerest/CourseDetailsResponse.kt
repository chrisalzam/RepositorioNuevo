package com.r2devpros.consumerest


import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class CourseDetailsResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "programId") val programId: String,
    @Json(name = "area") val area: String,
    @Json(name = "instructors") val instructors: List<Instructor>,
    @Json(name = "startDate") val startDate: String,
    @Json(name = "endDate") val endDate: String,
    @Json(name = "location") val location: String,
    @Json(name = "durationHours") val durationHours: Int,
    @Json(name = "origin") val origin: List<String>,
    @Json(name = "organizationForm") val organizationForm: List<String>,
    @Json(name = "capacitationForm") val capacitationForm: List<CapacitationForm>,
    @Json(name = "type") val type: List<String>,
    @Json(name = "participants") val participants: List<Participant>
) {
    @Keep
    @JsonClass(generateAdapter = true)
    data class Instructor(
        @Json(name = "license") val license: Int,
        @Json(name = "name") val name: String,
        @Json(name = "category") val category: String,
        @Json(name = "adscriptionCenter") val adscriptionCenter: String,
        @Json(name = "specialty") val specialty: String
    )

    @Keep
    @JsonClass(generateAdapter = true)
    data class CapacitationForm(
        @Json(name = "type") val type: String,
        @Json(name = "description") val description: String
    )

    @Keep
    @JsonClass(generateAdapter = true)
    data class Participant(
        @Json(name = "name") val name: String,
        @Json(name = "registrationNumber") val registrationNumber: String,
        @Json(name = "position") val position: String,
        @Json(name = "office") val office: String,
        @Json(name = "curp") val curp: String,
        @Json(name = "max_study_level") val maxStudyLevel: String,
        @Json(name = "gender") val gender: List<String>
    )
}