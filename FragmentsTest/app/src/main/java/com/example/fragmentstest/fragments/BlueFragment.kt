package com.example.fragmentstest.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fragmentstest.R

class BlueFragment : Fragment() {
    var param = ""
    lateinit var mParam1: String
    lateinit var mParam2: String
    private lateinit var button: Button
    private var mListener: OnFragmentInteractionListener? = null

    companion object {
        val blueFragmentTAG = BlueFragment::class.java.simpleName
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        fun newInstance(param1: String, param2: String): BlueFragment {
            val fragment = BlueFragment()
            val args = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }

            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            mParam1 = arguments?.getString(ARG_PARAM1) ?: ""
            mParam2 = arguments?.getString(ARG_PARAM2) ?: ""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.blue_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button = activity?.findViewById(R.id.btnBlue) ?: return
        val editText = activity?.findViewById<EditText>(R.id.etBlue)

        param = mParam1 + mParam2

        editText?.setText(param)
        button.setOnClickListener {
            onButtonPressed(editText?.text.toString())
        }

        activity?.findViewById<TextView>(R.id.tvClose)?.setOnClickListener {
            mListener?.onCloseBlueFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    private fun onButtonPressed(value: String) {
        mListener?.onFragmentInteraction(value)
    }

    fun updateData(data: String) {
        val editText = activity?.findViewById<EditText>(R.id.etBlue) ?: return
        editText.setText(data)
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(value: String)
        fun onCloseBlueFragment()
    }
}
