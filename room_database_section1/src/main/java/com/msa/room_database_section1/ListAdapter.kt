package com.msa.room_database_section1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.msa.room_database_section1.databinding.ListItemBinding
import com.msa.room_database_section1.generated.callback.OnClickListener
import com.msa.room_database_section1.local.Person

class ListAdapter(private val personList:List<Person>,
                  private val clickListener:(Person)->Unit)
    :RecyclerView.Adapter<MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val layoutInflater=LayoutInflater.from(parent.context)
        val binding:ListItemBinding=
            DataBindingUtil.inflate(layoutInflater,R.layout.list_item,parent,false)
        return MyViewHolder(binding)
    }



    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(personList[position],clickListener)
    }
}

class MyViewHolder(val binding:ListItemBinding):RecyclerView.ViewHolder(binding.root){


    fun bind(person: Person,clickListener:(Person)->Unit){
        binding.nameText.text=person.username
        binding.emailText.text=person.email
        binding.cardItem.setOnClickListener {
            clickListener(person)
        }
    }
}