package com.msa.livedata_section1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {

     var total=MutableLiveData<Int>()

    init {
        total.value=124
    }

    fun setTotal(input:Int){
        total.value=(total.value)?.plus(input)
    }

}