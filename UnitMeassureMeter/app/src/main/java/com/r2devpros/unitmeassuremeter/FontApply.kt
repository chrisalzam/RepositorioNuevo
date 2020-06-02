package com.r2devpros.unitmeassuremeter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


object FontApply {
    fun apply(fontFormat: FontFormat, tv: TextView? = null, btn: Button? = null) {
        val v: View = when {
            tv != null -> {
                tv.apply {
                    fontFormat.let {
                        text = it.text
                        textSize = it.textSize
                        setTextColor(Color.parseColor(it.tColor))
                    }
                }
                tv
            }
            btn != null -> {
                btn.apply {
                    fontFormat.let {
                        text = it.text
                        textSize = it.textSize
                        setTextColor(Color.parseColor(it.tColor))
                    }
                }
                btn
            }
            else -> {
                return
            }
        }

        v.apply {
            fontFormat.let {
                setPadding(it.pStart, it.pTop, it.pBottom, it.pEnd)
                (layoutParams as ViewGroup.MarginLayoutParams).setMargins(
                    it.mStart,
                    it.mTop,
                    it.mEnd,
                    it.mBottom
                )

                try {
                    background = if (it.backgroundTransparent) {
                        ColorDrawable(resources.getColor(android.R.color.transparent, null))
                    } else {
                        ColorDrawable(Color.parseColor(it.backgroundColor))
                    }
                } catch (e: Exception) {
                    Log.d("FontApply_TAG", "apply: backgroundException: $e")
                }
            }
        }
    }
}