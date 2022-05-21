package com.msa.room_database_section1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msa.room_database_section1.local.Person
import com.msa.room_database_section1.local.PersonRepository
import kotlinx.coroutines.launch

class MainViewModel (private val repository: PersonRepository):ViewModel(){

    val persons=repository.persons

    val inputName=MutableLiveData<String?>()

    val inputEmail=MutableLiveData<String?>()

    val saveorUpdateButtonText=MutableLiveData<String>()

    val clearAllOrDeleteButtonText=MutableLiveData<String>()

    init {
        saveorUpdateButtonText.value="Save"
        clearAllOrDeleteButtonText.value="Clear"
    }


    fun saveOrUpdate(){
        val name:String=inputName.value!!
        val email:String=inputEmail.value!!
        insert(Person(0,name,email))
        inputName.value = null
        inputEmail.value = null
    }
    fun clearOrAllDelete(){
        clearAll()
    }

    fun insert(person: Person)=viewModelScope.launch {
            repository.insert(person)
        }

    fun update(person: Person)=viewModelScope.launch {
        repository.update(person)
    }

    fun delete(person: Person)=viewModelScope.launch {
        repository.delete(person)
    }
    fun clearAll()=viewModelScope.launch {
        repository.deleteAll()
    }


}