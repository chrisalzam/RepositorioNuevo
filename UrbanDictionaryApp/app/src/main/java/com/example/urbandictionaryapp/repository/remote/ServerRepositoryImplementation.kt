package com.example.urbandictionaryapp.repository.remote

import com.example.urbandictionaryapp.model.Definition
import com.example.urbandictionaryapp.repository.ApiResult
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import timber.log.Timber

class ServerRepositoryImplementation(
    private val serverAPI: ServerAPI
) : ServerRepository {
    override fun getDefinitionsAsync(term: String): Deferred<ApiResult<List<Definition>>> =
        GlobalScope.async {
            try {
                val result = serverAPI.getDefinitionsByTermAsync(
                    host = "mashape-community-urban-dictionary.p.rapidapi.com",
                    key = "a61a1d1143msha75bba8b74f07d4p13cbd1jsn580989349a45",
                    term = term
                ).await().list?.map { responseDefinition ->
                    Definition(
                        definition = responseDefinition.definition ?: "",
                        permalink = responseDefinition.permalink ?: "",
                        thumbsUp = responseDefinition.thumbs_up ?: 0,
                        soundUrls = responseDefinition.sound_urls ?: emptyList(),
                        author = responseDefinition.author ?: "",
                        word = responseDefinition.word ?: "",
                        id = responseDefinition.defid ?: 0,
                        currentVote = responseDefinition.current_vote ?: "",
                        writtenOn = responseDefinition.written_on ?: "",
                        example = responseDefinition.example ?: "",
                        thumbsDown = responseDefinition.thumbs_down ?: 0
                    )
                }
                if (result != null)
                    ApiResult.Ok(result)
                else
                    ApiResult.Ok(emptyList())

            } catch (e: Exception) {
                Timber.d("ServerRepositoryImplementation_TAG: getDefinitionsAsync: Exception: $e")
                ApiResult.Error(e)
            }
        }

}