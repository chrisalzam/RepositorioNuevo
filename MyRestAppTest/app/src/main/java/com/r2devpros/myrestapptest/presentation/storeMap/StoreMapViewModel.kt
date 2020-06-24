package com.r2devpros.myrestapptest.presentation.storeMap

import androidx.databinding.Bindable
import com.r2devpros.myrestapptest.model.Store
import com.r2devpros.myrestapptest.presentation.base.BaseViewModel

class StoreMapViewModel : BaseViewModel() {
    @get:Bindable
    var store: Store? = null
}