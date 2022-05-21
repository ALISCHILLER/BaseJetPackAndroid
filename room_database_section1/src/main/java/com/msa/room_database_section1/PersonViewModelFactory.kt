package com.msa.room_database_section1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.msa.room_database_section1.local.PersonRepository

class PersonViewModelFactory(private  val repository: PersonRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
           return MainViewModel(repository) as T

        }
        throw  IllegalAccessException("Unknown View Model class")
    }
}