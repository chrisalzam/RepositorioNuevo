package com.r2devpros.myrestapptest.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import com.r2devpros.myrestapptest.R
import com.r2devpros.myrestapptest.databinding.ActivityMainBinding
import com.r2devpros.myrestapptest.presentation.locator.LocatorViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModel<LocatorViewModel>()
    private lateinit var layoutBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        layoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        layoutBinding.lifecycleOwner = this
        layoutBinding.viewModel = viewModel

        wireOnPropertyChanged()
    }

    override fun onResume() {
        super.onResume()

        viewModel.getAvailableStoresByAddress()
    }

    private fun wireOnPropertyChanged() {
        Timber.d("MainActivity_TAG: wireOnPropertyChanged: ")
        viewModel.onPropertyChanged(BR.availableStores) {
            Timber.d("MainActivity_TAG: wireOnPropertyChanged: availableStores: ${viewModel.availableStores.size}")

            //populate a recyclerview
        }

        viewModel.onPropertyChanged(BR.address) {
            Timber.d("MainActivity_TAG: wireOnPropertyChanged: address: ${viewModel.address}")
            viewModel.getAvailableStoresByAddress()
        }
    }
}
