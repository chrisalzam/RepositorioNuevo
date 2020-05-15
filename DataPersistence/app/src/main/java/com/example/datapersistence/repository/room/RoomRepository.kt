package com.example.datapersistence.repository.room

import android.content.Context
import androidx.room.Room
import com.example.datapersistence.model.Person

class RoomRepository(context: Context) {
    private val roomDAO: RoomDAO

    init {
        val roomDatabase =
            Room.databaseBuilder(context, MyRoomDatabase::class.java, "my-room-database")
                .build()
        roomDAO = roomDatabase.roomDAO()
    }

    fun insertPerson(person: Person) = roomDAO.insertPerson(person)

    fun getPersons() = roomDAO.getPersons()

    fun getPersonsByName(name: String) = roomDAO.getPersonsByName(name)
}