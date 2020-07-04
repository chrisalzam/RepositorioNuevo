package com.example.cardviewpanelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cardviewpanelapp.databinding.ActivityMainBinding
import com.example.cardviewpanelapp.presentation.RVApplicationsAdapter
import com.example.cardviewpanelapp.presentation.util.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.util.Collections.emptyList

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModel<MainViewModel>()
    private lateinit var layoutBinding: ActivityMainBinding
    private lateinit var myAdapter: RVApplicationsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("MainActivity_TAG: onCreate: ")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setBinding()
        wireOnPropertyChanged()
        fillRecyclerView()
        viewModel.getApplications()

    }

    private fun setBinding() {
        Timber.d("MainActivity_TAG: setBinding: ")
        layoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        layoutBinding.lifecycleOwner = this
        layoutBinding.viewModel = viewModel
    }

    private fun wireOnPropertyChanged() {
        Timber.d("MainActivity_TAG: wireOnPropertyChanged: ")

        viewModel.onPropertyChanged(BR.availableApplications) {
            Timber.d("MainActivity_TAG: wireOnPropertyChanged: availableApplications: ${viewModel.availableApplications.size}")
            //populate a recyclerview
            myAdapter.itemList = viewModel.recyclerItemsViewModel
            viewModel.loading = false
        }

        viewModel.onPropertyChanged(BR.appId) {
            Timber.d("MainActivity_TAG: wireOnPropertyChanged: term: ${viewModel.appId}")
            viewModel.loading = true
            viewModel.getApplications()
        }

    }

    private fun fillRecyclerView() {
        Timber.d("MainActivity_TAG: fillRecyclerView: ")
        /*myAdapter = MyRecyclerViewAdapter {
        }
        myAdapter.itemList = viewModel.availableDefinitions*/
        myAdapter =
                RVApplicationsAdapter { _, applications ->
                    Timber.d("MainActivity_TAG: fillRecyclerView: OnDefinitionClicked ${applications.appId.length}")
                }
        myAdapter.itemList = emptyList()

        layoutBinding.rvApps.adapter = myAdapter
        layoutBinding.rvApps.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        }
    }