package com.blueray.montak.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blueray.montak.databinding.CategoryHomeRvItemBinding
import com.blueray.montak.interfaces.onItemClikc
import com.blueray.montak.model.CategroyItem
import com.blueray.montak.model.GetCategory


class HomeCategoryAdapter
// this down is the basic implementation of an adapter
    (
    var list : List<CategroyItem>,
    private val  onItemClikc: onItemClikc

)
    : RecyclerView.Adapter<HomeCategoryAdapter.MyViewHolder>() {



    inner class MyViewHolder(val binding : CategoryHomeRvItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CategoryHomeRvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        TODO("Not yet implemented")



        holder.binding.name.text = list[position].name
        holder.itemView.setOnClickListener {
            onItemClikc.onItemClick(position)
        }
    }

}