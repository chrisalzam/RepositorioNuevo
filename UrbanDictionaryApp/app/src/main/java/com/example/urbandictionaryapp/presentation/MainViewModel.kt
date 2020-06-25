package com.example.urbandictionaryapp.presentation

import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.example.urbandictionaryapp.model.Definition
import com.example.urbandictionaryapp.presentation.base.BaseViewModel
import com.example.urbandictionaryapp.repository.ApiResult
import com.example.urbandictionaryapp.repository.remote.ServerRepository
import com.example.urbandictionaryapp.repository.runOnResult
import timber.log.Timber

class MainViewModel(
    private val serverRepository: ServerRepository
) : BaseViewModel() {

    @get:Bindable
    var availableDefinitions = emptyList<Definition>()
        set(value) {
            Timber.d("MainViewModel_TAG: availableDefinitions: old: ${field.size}, new:${value.size}")
            //This property is Available Definitions
            field = value
            notifyPropertyChanged(BR.availableDefinitions)
            notifyChange()
        }

    @Bindable
    var term: String = "wat"
        set(value) {
            //Field = Current Value of the property
            //Value = New Value to be set to current Value
            field = value
            //notifyPropertyChanged update the UI of the
            notifyPropertyChanged(BR.term)

        }

    val availableDefinitionsCount: String
        get() = availableDefinitions.size.toString()

    fun getDefinitions() = background {
        Timber.d("MainViewModel_TAG: getDefinitions: ")

        serverRepository.getDefinitionsAsync(term).runOnResult {
            when (this) {
                is ApiResult.Error -> Timber.d("MainViewModel_TAG: getDefinitions: Error: $error")
                is ApiResult.Ok -> {
                    Timber.d("MainViewModel_TAG: getDefinitions: Ok")
                    availableDefinitions = result
                }
            }
        }
    }


}