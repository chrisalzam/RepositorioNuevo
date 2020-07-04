package com.example.cardviewpanelapp.presentation.home

import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.example.cardviewpanelapp.model.Application
import com.example.cardviewpanelapp.presentation.base.BaseViewModel
import com.example.cardviewpanelapp.repository.ApiResult
import com.example.cardviewpanelapp.repository.remote.ServerRepository
import com.example.cardviewpanelapp.repository.runOnResult
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
                is ApiResult.Error -> {
                    Timber.d("MainViewModel_TAG: getApplications: Error: $error")
                    loading = false
                }
                is ApiResult.Ok -> {
                    Timber.d("MainViewModel_TAG: getApplications: Ok")
                    availableApplications = result
                }
            }
        }
        Timber.d("MainViewModel_TAG: getApplications: $availableApplications")
    }
}