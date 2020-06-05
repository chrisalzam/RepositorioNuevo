package com.r2devpros.databindingtest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import com.r2devpros.databindingtest.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity_TAG", "onCreate: ")

        //region Bind Layout and View Model Variable
//        setContentView(R.layout.activity_main)
        val layoutBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        layoutBinding.lifecycleOwner = this
        layoutBinding.viewModel = viewModel
        //endregion

        //region Set Title from view model
        viewModel.title = getString(R.string.app_name)
        //endregion

        viewModel.onPropertyChanged(BR.age) {
            Log.d("MainActivity_TAG", "onCreate: onPropertyChanged: Age: ${viewModel.age}")
        }

        viewModel.onPropertyChanged(com.r2devpros.databindingtest.BR.valuesFromServer) {
            Log.d(
                "MainActivity_TAG",
                "onCreate: onPropertyChanged: ValuesFromServer: ${viewModel.valuesFromServer.size}"
            )

            val index = Random.nextInt(0, 3)
            Log.d("MainActivity_TAG", "onCreate: randomIndex: $index")

            viewModel.age = viewModel.valuesFromServer[index]
        }
    }
}
