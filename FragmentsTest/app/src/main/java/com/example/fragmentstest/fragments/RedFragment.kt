package com.example.fragmentstest.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.fragmentstest.R

class RedFragment : Fragment() {
    private lateinit var interactor: OnFragmentInteractor

    companion object {
        val redFragmentTAG = RedFragment::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.red_fragment_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val button = activity?.findViewById<Button>(R.id.btnSendToBlue)
        val editText = activity?.findViewById<EditText>(R.id.etRed)

        button?.setOnClickListener {
            interactor.onDataFromRed(editText?.text.toString())
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnFragmentInteractor) {
            interactor = context
        } else {
            throw RuntimeException("must implement interface (OnFragmentInteractor)")
        }
    }

    interface OnFragmentInteractor {
        fun onDataFromRed(data: String)
    }
}
