package com.r2devpros.myrestapptest.presentation.locator

import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.r2devpros.myrestapptest.model.Store
import com.r2devpros.myrestapptest.presentation.base.BaseViewModel
import com.r2devpros.myrestapptest.repository.ApiResult
import com.r2devpros.myrestapptest.repository.remote.ServerRepository
import com.r2devpros.myrestapptest.repository.runOnResult
import timber.log.Timber

class LocatorViewModel(
    private val serverRepository: ServerRepository
) : BaseViewModel() {
    @get:Bindable
    var availableStores = emptyList<Store>()
        set(value) {
            field = value
            notifyPropertyChanged(BR.availableStores)
            notifyChange()
        }

    @Bindable
    var address: String = "3031 Windy Ridge Parkway, 30339"
    set(value) {
        field = value
        notifyPropertyChanged(BR.address)
    }

    val availableStoresCount: String
        get() = availableStores.size.toString()

    fun getAvailableStoresByAddress() = background {
        Timber.d("LocatorViewModel_TAG: getAvailableStoresByAddress: ")
        serverRepository.getStoresByAddressAsync(address).runOnResult {
            when(this) {
                is ApiResult.Error -> Timber.d("LocatorViewModel_TAG: getAvailableStoresByAddress: error: $error")
                is ApiResult.Ok -> availableStores = result
            }
        }
    }
}