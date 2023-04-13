package com.msa.dagger_hilt_tutorial.data.api

import com.msa.dagger_hilt_tutorial.data.model.User
import retrofit2.Response

interface ApiHelper {
    suspend fun getusers():Response<List<User>>

}