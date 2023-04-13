package com.msa.dagger_hilt_tutorial.data.model

import com.squareup.moshi.Json

data class User(
    @Json(name = "id")
    val id:Int,
    @Json(name = "name")

    val name: String ,

    @Json(name = "email")

    val email: String ,

    @Json(name = "avatar")

    val avatar: String ,

)
