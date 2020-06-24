package com.example.urbandictionaryapp.repository.remote.response

import com.squareup.moshi.Json

data class DefinitionsResponse(
    @Json(name = "list") val list: List<Definition>?
) {
    data class Definition(
        @Json(name = "definition")      val definition: String?,
        @Json(name = "permalink")       val permalink: String?,
        @Json(name = "thumbs_up")       val thumbs_up: Int?,
        @Json(name = "sound_urls")      val sound_urls: List<String>?,
        @Json(name = "author")          val author: String?,
        @Json(name = "word")            val word: String?,
        @Json(name = "defid")           val defid: Int?,
        @Json(name = "current_vote")    val current_vote: String?,
        @Json(name = "written_on")      val written_on: String?,
        @Json(name = "example")         val example: String?,
        @Json(name = "thumbs_down")     val thumbs_down: Int?
    )
}