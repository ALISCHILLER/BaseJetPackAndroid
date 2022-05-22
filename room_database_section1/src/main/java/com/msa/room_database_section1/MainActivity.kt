package com.msa.room_database_section1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.msa.room_database_section1.databinding.ActivityMainBinding
import com.msa.room_database_section1.local.Person
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
    private lateinit var adapter:ListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        val dao:PersonDAO=PersonDataBase.getInstance(application).personDao
        val repository=PersonRepository(dao)
        val factory=PersonViewModelFactory(repository)
        mainViewModel=ViewModelProvider(this,factory).get(MainViewModel::class.java)
        binding.lifecycleOwner=this
        binding.myViewModel=mainViewModel
        initRecylerView()

        mainViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this,it,Toast.LENGTH_LONG).show()
            }
        })
    }
    private fun initRecylerView(){
        binding.personRecycler.layoutManager=LinearLayoutManager(this)
        adapter = ListAdapter({personItem:Person->listItemClick(personItem)})
        binding.personRecycler.adapter=adapter
        displayPersonList()
    }
    private fun displayPersonList(){
        mainViewModel.persons.observe(this, Observer {
            Log.i("MainActivity", it.toString())
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClick(person: Person){
//        Toast.makeText(this,"Select Item name is ${person.username}",Toast.LENGTH_LONG).show()
        mainViewModel.initUpdateAndDelete(person)
    }

}