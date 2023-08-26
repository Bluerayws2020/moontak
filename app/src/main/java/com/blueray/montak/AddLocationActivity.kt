package com.blueray.montak

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.blueray.montak.adapters.AddressesAdapter
import com.blueray.montak.databinding.ActivityAddLocationBinding
import com.blueray.montak.helper.ViewUtils.hide

class AddLocationActivity : BaseActivity() {

    private lateinit var binding : ActivityAddLocationBinding
    private lateinit var addressAdapter : AddressesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

//      setup app bar
        setUpActionBar()

//      setup Rv
        setupRecycler()

        // on add location click ogo to add location activity
        binding.addLocationBtn.setOnClickListener {
            startActivity(Intent(this,InnerAddNewLocationsActivity::class.java).apply {
                // todo add extras
            })
        }


    }

    // setup recycler
    private fun setupRecycler() {
        // initializing adapter
        addressAdapter = AddressesAdapter(listOf())
        binding.addressesRv.adapter = addressAdapter
        binding.addressesRv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true)// trying reversed layout
    }

    // setting up action bar
    private fun setUpActionBar() {
        binding.includedTap.back.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.includedTap.title.text = getString(R.string.address)
        binding.includedTap.menu.hide()
    }



}