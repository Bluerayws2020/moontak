package com.blueray.montak.adapters

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.blueray.montak.databinding.ProductsItemBinding
import com.blueray.montak.helper.ViewUtils.hide
import com.blueray.montak.interfaces.OnProductListener
import com.blueray.montak.model.ProudectModelItem
import com.example.aljabermall.helpers.HelperUtils

class ProductAdapter
// this down is the basic implementation of an adapter
    (
    // todo change list model
    val context:Context,
    var list: List<ProudectModelItem>,
    private val onProductListener: OnProductListener
    )
    : RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {


    inner class MyViewHolder(val binding : ProductsItemBinding): RecyclerView.ViewHolder(binding.root)
   ,        View.OnClickListener, CompoundButton.OnCheckedChangeListener {
        val productTitle = binding.productName
        val productImage = binding.productImage
        val productPrice = binding.price
        val addToCart = binding.addToCart

        //        val productUnit = binding.productUnit
//        private val productLayout = binding.productLayout
        val favCheck = binding.likeBtn
//        private val quantityCard = binding.quantityCard
//        private val addItem = binding.addItem
//        private val removeItem = binding.removeItem
//        val itemQuantity = binding.itemQuantity





        init {
            itemView.setOnClickListener(this)
//            addToCart.setOnClickListener(this)
//            addToCart.hide()
//            addItem.setOnClickListener(this)
//            removeItem.setOnClickListener(this)
            favCheck.setOnCheckedChangeListener(this)
        }
        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }

        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
            if (buttonView?.isPressed == true) {
                    if (isChecked) {
                        val pid = list[position].pid
                        onProductListener.addToFavourite(pid.toInt())
                    } else {
                        val favouriteId = list[position].fav
                        onProductListener.removeFromFavourite(favouriteId!!)
                    }
                }
        }
    }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ProductsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        val productItem = list[position]


            holder.binding.productName .text = productItem.title
        holder.binding.price .text = productItem.price.toString()


         val options = arrayOf("Carton", "Peice")

val spinner =  holder.binding.quantityTypeSpinner
        // Set up adapter for the spinner
        val adapter = ArrayAdapter(context, R.layout.simple_spinner_dropdown_item, options)
        holder.binding.quantityTypeSpinner.adapter = adapter

        // Pre-select "Option 2"
        spinner.setSelection(1)

        // Set an item selected listener if you need to respond to item selections
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // Handle item selection
                val selectedOption = options[position]
                // Use 'selectedOption' as needed
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle no item being selected if necessary
            }
        }




        holder.binding.likeBtn.setOnClickListener {
            onProductListener.addToFavourite(list[position].pid.toInt())
        }


        holder.favCheck.isChecked = productItem.fav != 0


        }




//        holder.binding.productImage.load(HelperUtils.BASE_URL + productItem.images.toString()) {
//            placeholder(R.drawable.image)
//            error(R.drawable.image)
//            scale(Scale.FIT)
//            crossfade(true)




}