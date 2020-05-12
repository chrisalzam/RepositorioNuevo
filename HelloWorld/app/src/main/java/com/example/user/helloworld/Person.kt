package com.example.user.helloworld

import android.os.Parcel
import android.os.Parcelable

class Person() : Parcelable {
    var name: String = ""
    var age: String = ""
    var position: String = ""

    constructor(parcel: Parcel) : this() {
        name = parcel.readString() ?: ""
        age = parcel.readString() ?: ""
        position = parcel.readString() ?: ""
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(age)
        parcel.writeString(position)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Person> {
        override fun createFromParcel(parcel: Parcel): Person {
            return Person(parcel)
        }

        override fun newArray(size: Int): Array<Person?> {
            return arrayOfNulls(size)
        }
    }
}