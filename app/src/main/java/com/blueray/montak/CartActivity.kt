package com.blueray.montak

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.blueray.montak.adapters.CartAdapter
import com.blueray.montak.databinding.ActivityCartBinding
import com.blueray.montak.helper.ViewUtils.hide

class CartActivity : BaseActivity() {
    private lateinit var binding : ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // setUp recyclers
        setUpCartRec()

        // setup ActionBar
        setUpActionBar()

        // continue to payment
        binding.continueToPayBtn.setOnClickListener {
            startActivity(Intent(this,ContinueToPayment::class.java).apply {
                //todo add extra
            })

        }

    }

    // cart recycler setUp
    private fun setUpCartRec() {

        binding.cartRv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.cartRv.adapter = CartAdapter(listOf())
    }

    // setting up action bar
    private fun setUpActionBar() {
        binding.includedTap.back.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.includedTap.title.text = getString(R.string.cart)
        binding.includedTap.menu.hide()
    }
}