package com.r2devpros.consumerest

data class Participant(
    val curp: String,
    val gender: List<String>,
    val max_study_level: String,
    val name: String,
    val office: String,
    val position: String,
    val registrationNumber: String
)