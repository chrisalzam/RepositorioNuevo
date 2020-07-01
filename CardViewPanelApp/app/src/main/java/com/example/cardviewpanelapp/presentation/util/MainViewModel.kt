package com.example.cardviewpanelapp.presentation.util

import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.example.cardviewpanelapp.model.MyApplications
import com.example.cardviewpanelapp.presentation.recyclerview.MyApplicationsItemViewModel
import com.example.cardviewpanelapp.presentation.base.BaseViewModel
import com.example.cardviewpanelapp.repository.ApiResult
import com.example.cardviewpanelapp.repository.remote.ServerRepository
import com.example.cardviewpanelapp.repository.runOnResult
import timber.log.Timber

class MainViewModel(
    private val serverRepository: ServerRepository
) : BaseViewModel() {

    @get:Bindable
    var availableApplications = emptyList<MyApplications>()
        set(value) {
            Timber.d("MainViewModel_TAG: availableDefinitions: old: ${field.size}, new:${value.size}")
            //This property is Available Definitions
            field = value

            recyclerItemsViewModel = value.map {
                MyApplicationsItemViewModel()
                    .apply {
                    myApplicationsModel = it
                }
            }.toMutableList()

            notifyPropertyChanged(BR.availableApplications)
            notifyChange()
        }

    var recyclerItemsViewModel = mutableListOf<MyApplicationsItemViewModel>()
        private set

    @Bindable
    var appId: String = "1"
        set(value) {
            //Field = Current Value of the property
            //Value = New Value to be set to current Value
            field = value
            //notifyPropertyChanged update the UI of the
            notifyPropertyChanged(BR.appId)

        }



    fun getApplications() = background {
        Timber.d("MainViewModel_TAG: getDefinitions: ")

        serverRepository.getMyApplicationsAsync(appId).runOnResult {
            when (this) {
                is ApiResult.Error -> Timber.d("MainViewModel_TAG: getDefinitions: Error: $error")
                is ApiResult.Ok -> {
                    Timber.d("MainViewModel_TAG: getDefinitions: Ok")
                    availableApplications = result
                }
            }
        }
    }

    fun sortByNameOrId(byNameSorted: Boolean = true) {
        Timber.d("MainViewModel_TAG: sortByDefinitions: sortByDefinitions")
        availableApplications = if (byNameSorted) {
            availableApplications.sortedByDescending { it.name }
        } else {
            availableApplications.sortedByDescending { it.appId }
        }
    }

//    fun getSounds(soundId: Int): List<String> {
//        var sounds: MutableList<String> = mutableListOf()
//
//        for (definitionItem in availableApplications)
//        {
//            if(definitionItem.id == soundId)
//            {
//                for (soundUrlsItem in definitionItem.soundUrls)
//                    sounds.add(soundUrlsItem)
//            }
//        }
//        return sounds
//    }

}