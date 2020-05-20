package com.example.user.celebritydeathmatch

object MyData {
    //fun values() = arrayOf(
//    "Will Smith", "Hillary Clinton", "Quentin Tarantino",
//    "Madonna", "Mike Tyson", "Elton John")
//}
    fun values() = listOf(
        Celebrity(
            id = "1",
            name = "Will Smith",
            photoUrl = "https://vignette.wikia.nocookie.net/doblaje/images/c/c9/WillSmith.png/revision/latest/scale-to-width-down/85?cb=20170418000714&path-prefix=es",
            description = "THIS IS WILL"
        ),
        Celebrity(
            "2",
            "Hillary Clinton",
            "https://vignette.wikia.nocookie.net/doblaje/images/8/89/HillaryClinton.png/revision/latest/scale-to-width-down/85?cb=20170417233330&path-prefix=es",
            "THIS IS HILLARY"
        ),
        Celebrity(
            "3",
            "Quentin Tarantino",
            "https://vignette.wikia.nocookie.net/doblaje/images/d/d5/Quentin_Tarantino.png/revision/latest/scale-to-width-down/85?cb=20170418012900&path-prefix=es"
            , "THIS IS QUENTIN"
        ),
        Celebrity(
            "4",
            "Madonna",
            "https://vignette.wikia.nocookie.net/doblaje/images/b/b8/Madonna.png/revision/latest/scale-to-width-down/85?cb=20170417234244&path-prefix=es"
            , "THIS IS MADDONA"
        ),
        Celebrity(
            "5",
            "Mike Tyson",
            "https://vignette.wikia.nocookie.net/doblaje/images/6/62/MikeTyson.png/revision/latest/scale-to-width-down/85?cb=20170418013545&path-prefix=es"
            , "THIS IS TYSON"
        ),
        Celebrity(
            "6",
            "Elton John",
            "https://vignette.wikia.nocookie.net/doblaje/images/b/ba/Elthon_John.png/revision/latest/scale-to-width-down/85?cb=20170418011208&path-prefix=es"
            , "THIS IS JHON"
        )
    )
}