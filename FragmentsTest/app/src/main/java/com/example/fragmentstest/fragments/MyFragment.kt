package com.example.fragmentstest.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentstest.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MyFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("MyFragment_TAG", "onAttach: ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MyFragment_TAG", "onCreate: ")
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("MyFragment_TAG", "onCreateView: ")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.my_fragment_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("MyFragment_TAG", "onActivityCreated: ")
    }

    override fun onStart() {
        super.onStart()
        Log.d("MyFragment_TAG", "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MyFragment_TAG", "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MyFragment_TAG", "onPause: ")
    }

    override fun onStop() {
        Log.d("MyFragment_TAG", "onStop: ")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d("MyFragment_TAG", "onDestroyView: ")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("MyFragment_TAG", "onDestroy: ")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d("MyFragment_TAG", "onDetach: ")
        super.onDetach()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
