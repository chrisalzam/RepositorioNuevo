package com.r2devpros.unitmeassuremeter

import android.graphics.Typeface

@Suppress("unused")
enum class FontStyle(val description: String, val value: Int) {
    NORMAL("Normal", Typeface.NORMAL),
    BOLD("Bold", Typeface.BOLD),
    ITALIC("Italic", Typeface.ITALIC),
    DEFAULT("Normal", Typeface.NORMAL)
    ;

    companion object {
        fun fromValue(value: Int) = values().firstOrNull { it.value == value } ?: DEFAULT
        fun fromDescription(description: String) = values().firstOrNull { it.description == description } ?: DEFAULT
    }
}