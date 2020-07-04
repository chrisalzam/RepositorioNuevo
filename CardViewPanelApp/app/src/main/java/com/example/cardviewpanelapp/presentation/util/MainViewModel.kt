package com.example.cardviewpanelapp.presentation.util

import android.media.Image
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.example.cardviewpanelapp.model.Application
import com.example.cardviewpanelapp.presentation.recyclerview.MyApplicationsItemViewModel
import com.example.cardviewpanelapp.presentation.base.BaseViewModel
import com.example.cardviewpanelapp.repository.ApiResult
import com.example.cardviewpanelapp.repository.remote.ServerRepository
import com.example.cardviewpanelapp.repository.runOnResult
import okhttp3.internal.immutableListOf
import timber.log.Timber
import java.util.Collections.emptyList

class MainViewModel(
    private val serverRepository: ServerRepository
) : BaseViewModel() {

    @get:Bindable
    var availableApplications = emptyList<Application>()
        set(value) {
            Timber.d("MainViewModel_TAG: availableApplications: ${value.size}")
            //This property is Available Apps
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

//    var appIcon: String = "abc"
//    set(value) {
//        field = value
//        notifyPropertyChanged(BR.appIcon)
//    }

    val availableApplicationsCount: String
        get() = availableApplications.size.toString()

    fun getApplications() = background {
        Timber.d("MainViewModel_TAG: getApplications: ")

        serverRepository.getApplicationsAsync().runOnResult {
            when (this) {
                is ApiResult.Error -> Timber.d("MainViewModel_TAG: getApplications: Error: $error")
                is ApiResult.Ok -> {
                    Timber.d("MainViewModel_TAG: getApplications: Ok")
                    availableApplications = result
                }
            }
        }
        Timber.d("MainViewModel_TAG: getApplications: $availableApplications")
    }


//region Sort and Sound
//    fun sortByNameOrId(byNameSorted: Boolean = true) {
//        Timber.d("MainViewModel_TAG: sortByDefinitions: sortByDefinitions")
//        availableApplications = if (byNameSorted) {
//            availableApplications.sortedByDescending { it.name }
//        } else {
//            availableApplications.sortedByDescending { it.appId }
//        }
//    }

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
//endregion
}