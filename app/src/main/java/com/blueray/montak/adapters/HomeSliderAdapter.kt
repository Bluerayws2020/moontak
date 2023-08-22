package com.blueray.montak.adapters

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.blueray.montak.adapters.HomeDealsAdapter.*
import com.blueray.montak.databinding.Slider2Binding


class HomeSliderAdapter
    // this down is the basic implementation of an adapter
    (
    // todo change list model
    val context: Context,
    var list : List<String>
            )
    :RecyclerView.Adapter<HomeSliderAdapter.MyViewHolders>() {

    init {
        list = listOf()
    }

    inner class MyViewHolders(val binding : Slider2Binding):ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolders {
        val binding = Slider2Binding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolders(binding)
    }

    override fun getItemCount(): Int = 3

    override fun onBindViewHolder(holder: MyViewHolders, position: Int) {
//        TODO("Not yet implemented")



        holder.binding.counter.setTime(1, 1, 30, 15) // setTime(days, hours, minute, second)
        holder.binding.counter.startTimer()

    }

}