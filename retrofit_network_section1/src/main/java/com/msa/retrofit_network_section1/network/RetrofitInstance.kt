package com.msa.retrofit_network_section1.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/**
 * Created by Ali Soleimani on 08,May,2021
 * Github https://github.com/ALISCHILLER
 * in Iran.
 */
class RetrofitInstance {


    companion object{
        val BASE_URL ="https://jsonplaceholder.typicode.com/"

        val interceptor = HttpLoggingInterceptor().apply{
            this.level=HttpLoggingInterceptor.Level.BODY
        }
        val client=OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()

        fun getRetrofitInstance():Retrofit{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }
}