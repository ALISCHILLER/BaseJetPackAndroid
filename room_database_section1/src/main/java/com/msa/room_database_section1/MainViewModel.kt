package com.msa.room_database_section1

import android.util.Patterns
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

    private val statusMessage=MutableLiveData<Event<String>>()
    val message:LiveData<Event<String>>
    get()=statusMessage

    init {
        saveorUpdateButtonText.value="Save"
        clearAllOrDeleteButtonText.value="Clear"
    }


    fun saveOrUpdate(){
        if (inputName.value==null){
            statusMessage.value= Event("Please enter person's name")
        }else if (inputEmail.value==null){
            statusMessage.value= Event("Please enter person's email")
        }else if (Patterns.EMAIL_ADDRESS.matcher(inputEmail.value!!).matches()){
            statusMessage.value= Event("Please enter a correct email address")
        }else{
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
        statusMessage.value= Event("Person Inserted Successfully")
        }

    fun update(person: Person)=viewModelScope.launch {
        repository.update(person)
        inputName.value=null
        inputEmail.value=null
        isUpdateOrDelete=false
        saveorUpdateButtonText.value="Save"
        clearAllOrDeleteButtonText.value="Clear All"
        statusMessage.value=Event("Person Updated Successfully")
    }

    fun delete(person: Person)=viewModelScope.launch {
        repository.delete(person)
        inputName.value=null
        inputEmail.value=null
        isUpdateOrDelete=false
        saveorUpdateButtonText.value="Save"
        clearAllOrDeleteButtonText.value="Clear All"
        statusMessage.value=Event("Person Deleted Successfully")
    }
    fun clearAll()=viewModelScope.launch {
        repository.deleteAll()
        statusMessage.value=Event("All Persons Deleted Successfully")
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