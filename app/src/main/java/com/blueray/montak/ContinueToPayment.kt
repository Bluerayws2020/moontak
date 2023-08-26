package com.blueray.montak

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.blueray.montak.adapters.ContinueToPaymentAdapter
import com.blueray.montak.databinding.ActivityContinueToPaymentBinding
import com.blueray.montak.helper.ViewUtils.hide

class ContinueToPayment : BaseActivity() {

    private lateinit var binding : ActivityContinueToPaymentBinding
    private lateinit var adapter : ContinueToPaymentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContinueToPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set up app bar
        setUpActionBar()

        // set up recycler
        setupRV()

    }

    //setting up rv
    private fun setupRV() {
        // initializing adapter
        adapter = ContinueToPaymentAdapter(listOf())
        binding.itemsRv.adapter = adapter
        binding.itemsRv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true)// testing reversed list
    }

    // setting up action bar
    private fun setUpActionBar() {
        binding.includedTap.back.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.includedTap.title.text = getString(R.string.continue_to_pay)
        binding.includedTap.menu.hide()
    }
}