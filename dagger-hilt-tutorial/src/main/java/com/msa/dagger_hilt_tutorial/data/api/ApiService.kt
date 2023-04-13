package com.msa.dagger_hilt_tutorial.data.api

import com.msa.dagger_hilt_tutorial.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun  getUserts():Response<List<User>>

}