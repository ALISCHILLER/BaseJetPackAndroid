package com.msa.retrofit_network_section1.network

import com.msa.retrofit_network_section1.model.Albums
import com.msa.retrofit_network_section1.model.AlbumsItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
/**
 * Created by Ali Soleimani on 08,May,2021
 * Github https://github.com/ALISCHILLER
 * in Iran.
 */
interface AlbumService {


    @GET("/albums")
    suspend fun getAlbums():Response<Albums>
    @GET("/albums")
    suspend fun getSortedAlbums(@Query("userId") userId:Int):Response<Albums>

    @GET("/albums/{id}")
    suspend fun getAlbum(@Path(value = "id")id:Int):Response<AlbumsItem>
}