package com.r2devpros.unitmeassuremeter

import android.graphics.Color
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView


object FontApply {
    fun apply(fontFormat: FontFormat, tv: TextView? = null, btn: Button? = null) {
        tv?.apply {
            fontFormat.let {
                text = it.text
                setPadding(it.pStart, it.pTop, it.pBottom, it.pEnd)
                (layoutParams as ViewGroup.MarginLayoutParams).setMargins(it.mStart, it.mTop, it.mEnd, it.mBottom)
                Log.d("FontApplyClass","start:${it.mStart} Top:${it.mTop} End:${it.mEnd} Bottom:${it.mBottom}")
                //textSize = it.textSize
                //setBackgroundColor(Color.parseColor("#${it.tColor}"))
            }
        }

        btn?.apply {
            fontFormat.let {
                text = it.text
                setPadding(it.pStart, it.pTop, it.pBottom, it.pEnd)
                (layoutParams as ViewGroup.MarginLayoutParams).setMargins(it.mStart, it.mTop, it.mEnd, it.mBottom)
                //textSize = it.textSize
                //setBackgroundColor(Color.parseColor("#${it.tColor}"))


            }
        }
    }
}