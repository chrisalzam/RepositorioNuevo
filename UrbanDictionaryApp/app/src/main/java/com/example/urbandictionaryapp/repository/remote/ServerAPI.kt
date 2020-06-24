package com.example.urbandictionaryapp.repository.remote

import com.example.urbandictionaryapp.repository.remote.response.DefinitionsResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ServerAPI {
    @GET("define")
    fun getDefinitionsByTermAsync(
        @Header("x-rapidapi-host") host: String,
        @Header("x-rapidapi-key") key: String,
        @Query(value = "term") term: String
    ) : Deferred<DefinitionsResponse>
}