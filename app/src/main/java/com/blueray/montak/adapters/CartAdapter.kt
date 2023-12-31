package com.blueray.montak.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blueray.montak.databinding.CartItemBinding

class CartAdapter // this down is the basic implementation of an adapter
    (
    // todo change list model
    var list : List<String>
)
    :RecyclerView.Adapter<CartAdapter.MyViewHolder>() {

    init {
        list = listOf()
    }

    inner class MyViewHolder(val binding : CartItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = 3

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        TODO("Not yet implemented")
    }

}