package com.r2devpros.unitmeassuremeter

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FontFormat(
    var text: String = "",
    var styleName: String = "MyStyle",
    var pTop: Int = 0,
    var pStart: Int = 0,
    var pEnd: Int = 0,
    var pBottom: Int = 0,
    var mTop: Int = 0,
    var mStart: Int = 0,
    var mEnd: Int = 0,
    var mBottom: Int = 0,
    var textSize: Float = 0f,
    var tColor: String = "",
    var fontFamily: String = "",
    var fontStyle: String = ""
) : Parcelable