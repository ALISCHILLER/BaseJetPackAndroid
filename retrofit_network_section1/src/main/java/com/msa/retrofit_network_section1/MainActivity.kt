package com.msa.retrofit_network_section1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.msa.retrofit_network_section1.model.Albums
import com.msa.retrofit_network_section1.model.AlbumsItem
import com.msa.retrofit_network_section1.network.AlbumService
import com.msa.retrofit_network_section1.network.RetrofitInstance
import retrofit2.Response
/**
 * Created by Ali Soleimani on 08,May,2022
 * Github https://github.com/ALISCHILLER
 * in Iran.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var retService:AlbumService
    private lateinit var titile_text:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        titile_text=findViewById(R.id.titile_text)

      retService=RetrofitInstance.getRetrofitInstance()
            .create(AlbumService::class.java)

        /**Path Parameters*/
        getRequestWithPathParameters()
        /**get all parameters &Query Parameters */
        getRequestWithQueryParameters()
        /**Post Parameters  uploadAlbum*/
       // uploadAlbum()




    }
    /**Path Parameters*/
    private fun getRequestWithPathParameters(){
        /**Path Parameters*/
        val pathResponse:LiveData<Response<AlbumsItem>> = liveData{
            val response=retService.getAlbum(23)
            emit(response)
        }
        pathResponse.observe(this, Observer {
            val title=it.body()?.title
            Toast.makeText(applicationContext, title, Toast.LENGTH_SHORT).show()
        })
    }


    private fun getRequestWithQueryParameters(){
        val responseLiveData:LiveData<Response<Albums>> = liveData {
            /**get all parameters */
            ///val  response=retService.getAlbums()
            /**Query Parameters*/
            val response=retService.getSortedAlbums(3)
            emit(response)
        }
        responseLiveData.observe(this, Observer {
            val albumslist=it.body()?.listIterator()
            if (albumslist!=null){
                while (albumslist.hasNext()){
                    val albumsItem=albumslist.next()
                    Log.i("MainActivity",  albumsItem.title)
                    val result:String=" "+"Album Title: ${albumsItem.title}"+"\n"+
                            " "+"Album id:    ${albumsItem.id}"+"\n"+
                            " "+"UserId:${albumsItem.userId}"+"\n\n\n"

                    titile_text.append(result)
                }
            }
        })
    }

    /**Post Parameters  uploadAlbum*/
     private fun uploadAlbum(){
         val album=AlbumsItem(0,"My Titile",3)
         val postResponse:LiveData<Response<AlbumsItem>> = liveData {
             val response=retService.uploadAlbum(album)
             emit(response)
         }
         postResponse.observe(this, Observer {
             val receivedAlbumsItem =  it.body()
             val result:String=" "+"Album Title: ${receivedAlbumsItem?.title}"+"\n"+
                     " "+"Album id:    ${receivedAlbumsItem?.id}"+"\n"+
                     " "+"UserId:${receivedAlbumsItem?.userId}"+"\n\n\n"

             titile_text.append(result)
         })
    }
}