package com.msa.room_database_section1.local

import androidx.lifecycle.LiveData
import androidx.room.*


/**
 * Created by Ali Soleimani on 08,May,2021
 * Github https://github.com/ALISCHILLER
 * in Iran.
 */
@Dao
interface PersonDAO {

    @Insert
    suspend fun insertPerson(person: Person):Long

    @Update
    suspend fun updatePerson(person: Person)


    @Delete
    suspend fun deletePerson(person: Person)

    @Query("DELETE FROM person_data_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM person_data_table")
    fun getAllPerson():LiveData<List<Person>>

    /**
     *Other methods
     *  متد های دیگر
     */
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun insertPerson2(person: Person):Long
//
//    @Insert
//    fun insertPersons(person1:Person,person2: Person,person3: Person):List<Long>
//
//    @Insert
//    fun insertPersons(persons: List<Person>):List<Long>
//
//    @Insert
//    fun insertPersons2(person: Person,persons: List<Person>):List<Long>


}