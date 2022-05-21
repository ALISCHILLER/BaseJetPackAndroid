package com.msa.room_database_section1.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Person::class], version = 1)
abstract class PersonDataBase:RoomDatabase() {

    abstract val personDao:PersonDAO

    companion object{
        @Volatile
        private var INSTANCE:PersonDataBase?=null
        fun getInstance(context:Context):PersonDataBase{
            synchronized(this){
                var instance:PersonDataBase?= INSTANCE
                if (instance==null){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        PersonDataBase::class.java,
                        "person_database"
                    ).build()
                }
                return instance
            }
        }
    }
}