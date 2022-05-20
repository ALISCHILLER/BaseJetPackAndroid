package com.msa.livedata_viewmodel_databinding_section1

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {

   private var textplus=MutableLiveData<Int>()
    val plusData:LiveData<Int>
    get() = textplus


    private var totext=MutableLiveData<String>()
    val textData:LiveData<String>
    get() = totext

    @Bindable
    val inputText=MutableLiveData<String>()
    @Bindable
    val username=MutableLiveData<String>()

    init {
        textplus.value=0
        username.value="Steve"
    }

    fun setText(){
        var input:String=inputText.value!!.toString()
        totext.value=input
    }
    fun setAddPlus(){
        textplus.value=(textplus.value)?.plus(1)
    }

}