package com.msa.livedata_section1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
/**
 * Created by atp on 1/14/2017.
 * صفحه لیست مشتریان
 * edited by moji
 */

class MainViewModel:ViewModel() {

     var total=MutableLiveData<Int>()

    init {
        total.value=124
    }

    fun setTotal(input:Int){
        total.value=(total.value)?.plus(input)
    }

}