package com.msa.livedata_viewmodel_databinding_section1

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {

   private var textplus=MutableLiveData<Int>()
    val plusData:LiveData<Int>
    get() = textplus


    @Bindable
    val username=MutableLiveData<String>()

    init {
        textplus.value=0
        username.value="Steve"
    }
    fun setAddPlus(){
        textplus.value=(textplus.value)?.plus(1)
    }

}