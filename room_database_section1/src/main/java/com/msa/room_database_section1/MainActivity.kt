package com.msa.room_database_section1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.msa.room_database_section1.databinding.ActivityMainBinding
import com.msa.room_database_section1.local.PersonDAO
import com.msa.room_database_section1.local.PersonDataBase
import com.msa.room_database_section1.local.PersonRepository

/**
 * Created by Ali Soleimani on 08,May,2021
 * Github https://github.com/ALISCHILLER
 * in Iran.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var mainViewModel:MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        val dao:PersonDAO=PersonDataBase.getInstance(application).personDao
        val repository=PersonRepository(dao)
        val factory=PersonViewModelFactory(repository)
        mainViewModel=ViewModelProvider(this,factory).get(MainViewModel::class.java)
        binding.lifecycleOwner=this
        binding.myViewModel=mainViewModel
        displayPersonList()
    }
    private fun displayPersonList(){
        mainViewModel.persons.observe(this, Observer {
            Log.i("mytag",it.toString())
        })
    }
}