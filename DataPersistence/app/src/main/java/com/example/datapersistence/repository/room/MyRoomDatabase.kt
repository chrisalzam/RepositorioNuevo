package com.example.datapersistence.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.datapersistence.model.Person

@Database(entities = [Person::class], version = 1, exportSchema = false)
abstract class MyRoomDatabase : RoomDatabase() {
    abstract fun roomDAO(): RoomDAO
}