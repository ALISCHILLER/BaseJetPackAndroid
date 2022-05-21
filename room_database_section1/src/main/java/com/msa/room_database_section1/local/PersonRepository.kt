package com.msa.room_database_section1.local

class PersonRepository (private val dao:PersonDAO){
    val persons=dao.getAllPerson()

    suspend fun insert(person: Person){
        dao.insertPerson(person)
    }

    suspend fun update(person: Person){
        dao.updatePerson(person)
    }

    suspend fun delete(person: Person){
        dao.deletePerson(person)
    }

    suspend fun deleteAll(){
        dao.deleteAll()
    }
}