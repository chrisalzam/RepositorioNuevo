package com.r2devpros.myrestapptest.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.r2devpros.myrestapptest.R
import com.r2devpros.myrestapptest.databinding.ActivityMainBinding
import com.r2devpros.myrestapptest.model.Store
import com.r2devpros.myrestapptest.presentation.locator.LocatorViewModel
import com.r2devpros.myrestapptest.presentation.storeMap.StoreMapActivity
import com.r2devpros.myrestapptest.presentation.util.MyRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var myAdapter: MyRecyclerViewAdapter
    private val viewModel by viewModel<LocatorViewModel>()
    private lateinit var layoutBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("MainActivity_TAG: onCreate: ")
//        setContentView(R.layout.activity_main)
        layoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        layoutBinding.lifecycleOwner = this
        layoutBinding.viewModel = viewModel
        viewModel.loading = true
        wireOnPropertyChanged()

        fillRecyclerView()
    }

    private fun fillRecyclerView() {
        Timber.d("MainActivity_TAG: fillRecyclerView: ")
        myAdapter = MyRecyclerViewAdapter {
            onStoreClicked(it)
        }
        myAdapter.itemList = viewModel.availableStores
        layoutBinding.rvStores.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        }
    }

    private fun onStoreClicked(store: Store) {
        Timber.d("MainActivity_TAG: onStoreClicked: $store")
        val intent = Intent(this, StoreMapActivity::class.java)
        intent.putExtra(GOOGLE_MAP_FIRST_KEY, Bundle().apply {
            putString(GOOGLE_MAP_ID_STORE_KEY, store.id)
            putString(GOOGLE_MAP_ADDRESS_STORE_KEY, store.address)
            putString(GOOGLE_MAP_HOURS_SERVICE_STORE_KEY, store.serviceHoursDescription)
            putString(GOOGLE_MAP_PHONE_STORE_KEY, store.phone)
            putDouble(GOOGLE_MAP_LATITUDE_STORE_KEY, store.latitude)
            putDouble(GOOGLE_MAP_LONGITUDE_STORE_KEY, store.longitude)
        })

        startActivity(intent)

    }

    override fun onResume() {
        super.onResume()
        Timber.d("MainActivity_TAG: onResume: ")
        viewModel.getAvailableStoresByAddress()
    }

    private fun wireOnPropertyChanged() {
        Timber.d("MainActivity_TAG: wireOnPropertyChanged: ")

        viewModel.onPropertyChanged(BR.availableStores) {
            Timber.d("MainActivity_TAG: wireOnPropertyChanged: availableStores: ${viewModel.availableStores.size}")
            //populate a recyclerview
            myAdapter.itemList = viewModel.availableStores
            viewModel.loading = false
        }

        viewModel.onPropertyChanged(BR.address) {
            Timber.d("MainActivity_TAG: wireOnPropertyChanged: address: ${viewModel.address}")
            viewModel.loading = true
            viewModel.getAvailableStoresByAddress()
        }
    }
}
