package com.example.datapersistence.repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.datapersistence.model.Person

@Dao
interface RoomDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPerson(person: Person)

    @Query("SELECT * FROM person")
    fun getPersons(): List<Person>

    @Query("SELECT * FROM person AS a WHERE a.name LIKE '%' || :name || '%'")
    fun getPersonsByName(name: String): List<Person>
}