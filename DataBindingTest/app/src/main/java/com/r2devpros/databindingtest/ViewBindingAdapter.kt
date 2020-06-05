package com.r2devpros.databindingtest

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("android:visibility")
fun visibility(view: View, visible: Boolean?) = when (visible) {
    true -> view.visibility = View.VISIBLE
    else -> view.visibility = View.GONE
}