package com.example.fragmentstest

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fragmentstest.fragments.BlueFragment
import com.example.fragmentstest.fragments.RedFragment

class MainActivity : AppCompatActivity(), RedFragment.OnFragmentInteractor,
    BlueFragment.OnFragmentInteractionListener {

    //region Life Cycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MainActivity_TAG", "onCreate: ")
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity_TAG", "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity_TAG", "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity_TAG", "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity_TAG", "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity_TAG", "onDestroy: ")
    }
    //endregion

    fun onAddFragment(view: View) {
        Log.d("MainActivity_TAG", "onAddFragment: ")

        when (view.id) {
            R.id.btnAddFragment -> {
                var redFragment =
                    supportFragmentManager.findFragmentByTag(RedFragment.redFragmentTAG)
                if (redFragment == null) {
                    redFragment = RedFragment()
                    supportFragmentManager.beginTransaction()
                        .add(R.id.fragHolder1, redFragment, RedFragment.redFragmentTAG)
                        .addToBackStack(RedFragment.redFragmentTAG)
                        .commit()
                }
            }
            else -> {
                var blueFragment = supportFragmentManager.findFragmentById(R.id.fragHolder2)
                if (blueFragment == null) {
                    blueFragment = BlueFragment.newInstance("data 1", "data 2")

                    supportFragmentManager.beginTransaction()
                        .add(R.id.fragHolder2, blueFragment)
                        .addToBackStack(BlueFragment.blueFragmentTAG)
                        .commit()
                }
            }
        }
    }

    fun onRemoveFragments(view: View) {
        Log.d("MainActivity_TAG", "onRemoveFragments: ")
        supportFragmentManager.fragments.forEach {
            supportFragmentManager.beginTransaction().remove(it).commit()
            supportFragmentManager.popBackStackImmediate()
        }
    }

    override fun onDataFromRed(data: String) {
        Log.d("MainActivity_TAG", "onDataFromRed: $data")
        var blueFragment = supportFragmentManager.findFragmentById(R.id.fragHolder2)

        if (blueFragment != null) {
            (blueFragment as BlueFragment).updateData(data)
        } else {
            blueFragment = BlueFragment.newInstance("Data", data)

            supportFragmentManager.beginTransaction()
                .add(R.id.fragHolder2, blueFragment, BlueFragment.blueFragmentTAG)
                .addToBackStack(BlueFragment.blueFragmentTAG)
                .commit()
        }
    }

    override fun onFragmentInteraction(value: String) {
        Log.d("MainActivity_TAG", "onFragmentInteraction: $value")
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show()
    }

    override fun onCloseBlueFragment() {
        Log.d("MainActivity_TAG", "onCloseBlueFragment: ")
        val blueFragment = supportFragmentManager.findFragmentById(R.id.fragHolder2)

        if (blueFragment != null) {
            supportFragmentManager.beginTransaction().remove(blueFragment).commit()
            supportFragmentManager.popBackStackImmediate()
        } else {
            Toast.makeText(this, "No Blue Fragment Found", Toast.LENGTH_SHORT).show()
        }
    }
}
