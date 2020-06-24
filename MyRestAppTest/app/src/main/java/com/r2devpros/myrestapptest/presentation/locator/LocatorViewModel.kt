package com.r2devpros.myrestapptest.presentation.locator

import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.r2devpros.myrestapptest.model.Store
import com.r2devpros.myrestapptest.presentation.base.BaseViewModel
import com.r2devpros.myrestapptest.repository.ApiResult
import com.r2devpros.myrestapptest.repository.remote.ServerRepository
import com.r2devpros.myrestapptest.repository.runOnResult
import kotlinx.coroutines.Delay
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import okhttp3.internal.wait
import timber.log.Timber

class LocatorViewModel(
    private val serverRepository: ServerRepository
) : BaseViewModel() {
    @get:Bindable
    var availableStores = emptyList<Store>()
        set(value) {
            Timber.d("LocatorViewModel_TAG: availableStores: old: ${field.size}, new:${value.size}")
            //This property is AvailableStores
            field = value
            notifyPropertyChanged(BR.availableStores)
            notifyChange()
        }

    @Bindable
    var address: String = "3031 Windy Ridge Parkway, 30339"
        set(value) {
            //Field = Current Value of the property
            //Value = New Value to be set to current Value
            field = value
            //notifyPropertyChanged update the UI of the
            notifyPropertyChanged(BR.address)

        }

    val availableStoresCount: String
        get() = availableStores.size.toString()

    fun getAvailableStoresByAddress() = background {
        Timber.d("LocatorViewModel_TAG: getAvailableStoresByAddress: ")

        //delay(4000)
        serverRepository.getStoresByAddressAsync(address).runOnResult {
            when (this) {
                is ApiResult.Error -> Timber.d("LocatorViewModel_TAG: getAvailableStoresByAddress: error: $error")
                is ApiResult.Ok -> {
                    Timber.d("LocatorViewModel_TAG: getAvailableStoresByAddress: Ok")
                    availableStores = result
                }
            }
        }
    }
}