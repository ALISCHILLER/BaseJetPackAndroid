package com.msa.livedata_viewmodel_databinding_section1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.msa.livedata_viewmodel_databinding_section1.databinding.ActivityMainBinding
/**
 * Created by Ali Soleimani on 08,May,2021
 * Github https://github.com/ALISCHILLER
 * in Iran.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val mainViewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.lifecycleOwner=this
        binding.myViewModel=mainViewModel
    }
}