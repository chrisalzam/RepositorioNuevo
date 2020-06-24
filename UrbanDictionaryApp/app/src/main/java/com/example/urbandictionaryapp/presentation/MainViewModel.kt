package com.example.urbandictionaryapp.presentation

import com.example.urbandictionaryapp.presentation.base.BaseViewModel
import com.example.urbandictionaryapp.repository.ApiResult
import com.example.urbandictionaryapp.repository.remote.ServerRepository
import com.example.urbandictionaryapp.repository.runOnResult
import timber.log.Timber

class MainViewModel(
    private val serverRepository: ServerRepository
) : BaseViewModel() {
    fun getDefinitions() = background {
        Timber.d("MainViewModel_TAG: getDefinitions: ")

        serverRepository.getDefinitionsAsync("wat").runOnResult {
            when (this) {
                is ApiResult.Error -> Timber.d("MainViewModel_TAG: getDefinitions: Error: $error")
                is ApiResult.Ok -> Timber.d("MainViewModel_TAG: getDefinitions: ${result.size}")
            }
        }
    }
}