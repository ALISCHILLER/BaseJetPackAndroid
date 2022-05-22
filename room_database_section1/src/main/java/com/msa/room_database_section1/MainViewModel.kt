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
    private var isUpdateOrDelete=false
    private  lateinit var personToUpdateOrDelete:Person
    val inputName=MutableLiveData<String?>()

    val inputEmail=MutableLiveData<String?>()

    val saveorUpdateButtonText=MutableLiveData<String>()

    val clearAllOrDeleteButtonText=MutableLiveData<String>()

    init {
        saveorUpdateButtonText.value="Save"
        clearAllOrDeleteButtonText.value="Clear"
    }


    fun saveOrUpdate(){
        if (isUpdateOrDelete){
            personToUpdateOrDelete.username=inputName.value!!
            personToUpdateOrDelete.email=inputEmail.value!!

            update(personToUpdateOrDelete)
        }else{
            val name:String=inputName.value!!
            val email:String=inputEmail.value!!
            insert(Person(0,name,email))
            inputName.value = null
            inputEmail.value = null
        }

    }
    fun clearOrAllDelete(){
        if (isUpdateOrDelete){
            delete(personToUpdateOrDelete)
        }else{
            clearAll()
        }

    }

    fun insert(person: Person)=viewModelScope.launch {
            repository.insert(person)
        }

    fun update(person: Person)=viewModelScope.launch {
        repository.update(person)
        inputName.value=null
        inputEmail.value=null
        isUpdateOrDelete=false
        saveorUpdateButtonText.value="Save"
        clearAllOrDeleteButtonText.value="Clear All"
    }

    fun delete(person: Person)=viewModelScope.launch {
        repository.delete(person)
        inputName.value=null
        inputEmail.value=null
        isUpdateOrDelete=false
        saveorUpdateButtonText.value="Save"
        clearAllOrDeleteButtonText.value="Clear All"
    }
    fun clearAll()=viewModelScope.launch {
        repository.deleteAll()
    }

    fun initUpdateAndDelete(person: Person) {
        inputName.value=person.username
        inputEmail.value=person.email
        isUpdateOrDelete=true
        personToUpdateOrDelete=person
        saveorUpdateButtonText.value="Update"
        clearAllOrDeleteButtonText.value="Delete"

    }

}