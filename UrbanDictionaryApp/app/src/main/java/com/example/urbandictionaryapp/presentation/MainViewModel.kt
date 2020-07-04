package com.example.urbandictionaryapp.presentation

import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.example.urbandictionaryapp.generated.callback.OnClickListener
import com.example.urbandictionaryapp.model.Definition
import com.example.urbandictionaryapp.presentation.base.BaseViewModel
import com.example.urbandictionaryapp.presentation.recyclerview.DefinitionItemViewModel
import com.example.urbandictionaryapp.repository.ApiResult
import com.example.urbandictionaryapp.repository.remote.ServerRepository
import com.example.urbandictionaryapp.repository.runOnResult
import timber.log.Timber
import java.util.*
import java.util.Collections.emptyList

class MainViewModel(
    private val serverRepository: ServerRepository
) : BaseViewModel() {

    @get:Bindable
    var availableDefinitions = emptyList<Definition>()
        set(value) {
            Timber.d("MainViewModel_TAG: availableDefinitions: old: ${field.size}, new:${value.size}")
            //This property is Available Definitions
            field = value

            recyclerItemsViewModel = value.map {
                DefinitionItemViewModel().apply {
                    definitionModel = it
                }
            }.toMutableList()

            notifyPropertyChanged(BR.availableDefinitions)
            notifyChange()
        }

    var recyclerItemsViewModel = mutableListOf<DefinitionItemViewModel>()
        private set

    @Bindable
    var term: String = "wat"
        set(value) {
            //Field = Current Value of the property
            //Value = New Value to be set to current Value
            field = value
            //notifyPropertyChanged update the UI of the
            notifyPropertyChanged(BR.term)

        }



    fun getDefinitions() = background {
        Timber.d("MainViewModel_TAG: getDefinitions: ")

        serverRepository.getDefinitionsAsync(term).runOnResult {
            when (this) {
                is ApiResult.Error -> Timber.d("MainViewModel_TAG: getDefinitions: Error: $error")
                is ApiResult.Ok -> {
                    Timber.d("MainViewModel_TAG: getDefinitions: Ok")
                    availableDefinitions = result
                    getSounds(4)
                }
            }
        }
    }

    fun sortByDefinitions(moreLikes: Boolean = true) {
        Timber.d("MainViewModel_TAG: sortByDefinitions: sortByDefinitions")
        availableDefinitions = if (moreLikes) {
            availableDefinitions.sortedByDescending { it.thumbsUp }
        } else {
            availableDefinitions.sortedByDescending { it.thumbsDown }
        }
    }

    fun getSounds(soundId: Int): List<String> {
        var sounds: MutableList<String> = mutableListOf()

        for (definitionItem in availableDefinitions)
        {
            if(definitionItem.id == soundId)
            {
                for (soundUrlsItem in definitionItem.soundUrls)
                    sounds.add(soundUrlsItem)
            }
        }
        return sounds
    }

}