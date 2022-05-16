package com.msa.databindingsection1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.msa.databindingsection1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)

        /**
         * set the text with Databinding
         */
        binding.person=getPerson()

        /**
         * ست کردن متن قبل از Databinding
         * Set the text before Databinding
         */
//        val person:Person=getPerson()
//        binding.txtId.text= person.id.toString()
//        binding.txtName.text= person.name
//        binding.txtLastname.text= person.lastName
    }

    private fun getPerson():Person{
        return Person(1,"Alex","jonson")
    }
}