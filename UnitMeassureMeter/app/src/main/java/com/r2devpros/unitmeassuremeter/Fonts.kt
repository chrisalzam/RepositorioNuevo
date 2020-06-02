package com.r2devpros.unitmeassuremeter

@Suppress("EnumEntryName")
enum class Fonts(val description: String, val resourceName: String) {
    Gotham_Bold("Gotham Bold", "gotham_bold"),
    Gotham_Bold_Italic("Gotham Bold Italic", "gotham_bold_italic"),
    Gotham_Book("Gotham Book", "gotham_book"),
    Gotham_Book_Italic("Gotham Book Italic", "gotham_book_italic"),
    Gotham_Light("Gotham light", "gotham_light"),
    Gotham_Light_Italic("Gotham Light Italic", "gotham_light_italic"),
    Gotham_Medium("Gotham Medium", "gotham_medium"),
    Gotham_Medium_1("Gotham Medium 1", "gotham_medium_1"),
    Gotham_Medium_Italic("Gotham Medium Italic", "gotham_medium_italic")
    ;

    companion object {
        fun fromDescription(description: String) = values().firstOrNull { it.description == description }
    }
}