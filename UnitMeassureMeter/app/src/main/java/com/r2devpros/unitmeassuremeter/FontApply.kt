package com.r2devpros.unitmeassuremeter

import android.widget.Button
import android.widget.TextView

object FontApply {
    fun apply(fontFormat: FontFormat, tv: TextView? = null, btn: Button? = null) {
        tv?.apply {
            fontFormat.let {
                text = it.text
                setPadding(it.pStart, it.pTop, it.pBottom, it.pEnd)
            }
        }

        btn?.apply {
            fontFormat.let {
                text = it.text
                setPadding(it.pStart, it.pTop, it.pBottom, it.pEnd)
            }
        }
    }
}