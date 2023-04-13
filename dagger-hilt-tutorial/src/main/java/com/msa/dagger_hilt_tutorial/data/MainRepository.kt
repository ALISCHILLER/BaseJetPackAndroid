package com.msa.dagger_hilt_tutorial.data

import com.msa.dagger_hilt_tutorial.data.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private  val apiHelper: ApiHelper) {
    suspend fun getUsers()=apiHelper.getusers()

}