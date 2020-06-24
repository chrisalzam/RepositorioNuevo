package com.example.urbandictionaryapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Definition(
    var definition: String = "",
    var permalink: String = "",
    var thumbsUp: Int = 0,
    var soundUrls: List<String> = emptyList(),
    var author: String = "",
    var word: String = "",
    var id: Int = 0,
    var currentVote: String = "",
    var writtenOn: String = "",
    var example: String = "",
    var thumbsDown: Int = 0
):Parcelable