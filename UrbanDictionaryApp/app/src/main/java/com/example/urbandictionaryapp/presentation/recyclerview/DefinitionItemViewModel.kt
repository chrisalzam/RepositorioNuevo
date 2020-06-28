package com.example.urbandictionaryapp.presentation.recyclerview

import com.example.urbandictionaryapp.model.Definition
import com.example.urbandictionaryapp.presentation.base.BaseViewModel

class DefinitionItemViewModel : BaseViewModel() {
    var definitionModel: Definition? = null

    val id: String?
        get() = definitionModel?.id?.toString()

    val word: String?
        get() = definitionModel?.word

    val definition: String?
        get() = definitionModel?.definition

    val permanentLink: String?
        get() = definitionModel?.permalink

    val thumbsUp: String?
        get() = definitionModel?.thumbsUp?.toString()

    val thumbsDown: String?
        get() = definitionModel?.thumbsDown?.toString()

    val author: String?
        get() = definitionModel?.author

    val example: String?
        get() = definitionModel?.example

    val sound: List<String>?
        get() = definitionModel?.soundUrls

    val itemClickedId: Int?
    get() = id?.toInt()
}