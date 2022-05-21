package com.msa.livedata_section1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.msa.livedata_section1.databinding.ActivityMainBinding

/**
 * Created by atp on 1/14/2017.
 * صفحه لیست مشتریان
 * edited by moji
 */

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    // Use the 'by viewModels()' Kotlin property delegate
    // from the activity-ktx artifact
    private val model: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.viewmodel=model

        model.total.observe(this, Observer {
            binding.txtViewTotal.text=it.toString()
        })

    binding.btnAdd.setOnClickListener(View.OnClickListener {
        model.setTotal(binding.editText.text.toString().toInt())
    })
    }

}