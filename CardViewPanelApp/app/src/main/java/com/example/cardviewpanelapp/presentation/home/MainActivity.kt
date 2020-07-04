package com.example.cardviewpanelapp.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cardviewpanelapp.R
import com.example.cardviewpanelapp.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.util.Collections.emptyList

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModel<MainViewModel>()
    private lateinit var layoutBinding: ActivityMainBinding
    private lateinit var myAdapter: RVApplicationsAdapter

    //region Life Cycle
    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("MainActivity_TAG: onCreate: ")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setBinding()
        wireOnPropertyChanged()
    }

    override fun onResume() {
        super.onResume()

        initRecyclerView()
        viewModel.getApplications()
    }
    //endregion

    private fun setBinding() {
        Timber.d("MainActivity_TAG: setBinding: ")
        layoutBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        layoutBinding.lifecycleOwner = this
        layoutBinding.viewModel = viewModel
        viewModel.loading = true
    }

    private fun wireOnPropertyChanged() {
        Timber.d("MainActivity_TAG: wireOnPropertyChanged: ")

        viewModel.onPropertyChanged(BR.availableApplications) {
            Timber.d("MainActivity_TAG: wireOnPropertyChanged: availableApplications: ${viewModel.availableApplications.size}")
            //populate a recyclerview
            myAdapter.itemList = viewModel.recyclerItemsViewModel
            viewModel.loading = false
        }
    }

    private fun initRecyclerView() {
        Timber.d("MainActivity_TAG: fillRecyclerView: ")

        //region Create Adapter and set onItemClickListener (when the user clicks an item)
        myAdapter =
            RVApplicationsAdapter { _, application ->
                Timber.d("MainActivity_TAG: initRecyclerView: onAppClicked: ${application.name}")
            }
        //endregion

        //region init empty list for the recyclerview items
        myAdapter.itemList = emptyList()
        //endregion

        //region Apply configurations
        layoutBinding.rvApps.apply {
            adapter = myAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 4)
        }
        //endregion
    }
}