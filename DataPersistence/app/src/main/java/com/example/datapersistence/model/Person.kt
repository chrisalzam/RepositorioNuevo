package com.example.datapersistence.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "person")
@Parcelize
data class Person(
    @NonNull
    @PrimaryKey
    var personaId: String = java.util.UUID.randomUUID().toString(),

    var name: String,
    var age: Int,
    var position: String
) : Parcelable