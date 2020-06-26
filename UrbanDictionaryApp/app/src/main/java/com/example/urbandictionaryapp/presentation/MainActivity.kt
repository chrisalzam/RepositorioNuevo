package com.example.urbandictionaryapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.urbandictionaryapp.R
import com.example.urbandictionaryapp.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {
//    private lateinit var myAdapter: MyRecyclerViewAdapter
    private lateinit var myAdapter: RVDefinitionAdapter
    private val viewModel by viewModel<MainViewModel>()
    private lateinit var layoutBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        setBinding()
        viewModel.getDefinitions()
        wireOnPropertyChanged()

        fillRecyclerView()
    }

    private fun setBinding() {
        layoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        layoutBinding.lifecycleOwner = this
        layoutBinding.viewModel = viewModel
    }

    private fun fillRecyclerView() {
        Timber.d("MainActivity_TAG: fillRecyclerView: ")
        /*myAdapter = MyRecyclerViewAdapter {

        }
        myAdapter.itemList = viewModel.availableDefinitions*/
        myAdapter = RVDefinitionAdapter { _, definition ->
            Timber.d("MainActivity: onDefinitionClicked: $definition")
        }
        myAdapter.itemList = emptyList()

        layoutBinding.rvDefinitions.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        }
    }

    override fun onResume() {
        super.onResume()
        Timber.d("MainActivity_TAG: onResume: ")

    }

    private fun wireOnPropertyChanged() {
        Timber.d("MainActivity_TAG: wireOnPropertyChanged: ")

        viewModel.onPropertyChanged(BR.availableDefinitions) {
            Timber.d("MainActivity_TAG: wireOnPropertyChanged: availableDefinitions: ${viewModel.availableDefinitions.size}")
            //populate a recyclerview
            myAdapter.itemList = viewModel.recyclerItemsViewModel
            viewModel.loading = false
        }

        viewModel.onPropertyChanged(BR.term) {
            Timber.d("MainActivity_TAG: wireOnPropertyChanged: term: ${viewModel.term}")
            viewModel.loading = true
            viewModel.getDefinitions()
        }
    }
}