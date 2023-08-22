package com.blueray.montak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.blueray.montak.adapters.CartAdapter
import com.blueray.montak.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // setUp recyclers
        setUpCartRec()

    }

    // cart recycler setUp
    private fun setUpCartRec() {
        binding.cartRv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.cartRv.adapter = CartAdapter(listOf())
    }
}