package com.msa.room_database_section1.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Ali Soleimani on 08,May,2021
 * Github https://github.com/ALISCHILLER
 * in Iran.
 */
@Entity(tableName = "person_data_table")
data class Person(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="person_id")
    val id:Int,
    @ColumnInfo(name = "person_name")
    val username:String,
    @ColumnInfo(name = "person_email")
    val email:String

)