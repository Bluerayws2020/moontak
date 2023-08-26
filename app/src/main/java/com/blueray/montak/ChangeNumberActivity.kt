package com.blueray.montak

import android.os.Bundle
import com.blueray.montak.databinding.ActivityChangeNumberBinding
import com.blueray.montak.helper.ViewUtils.hide

class ChangeNumberActivity : BaseActivity() {

    private lateinit var binding : ActivityChangeNumberBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeNumberBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set up app bar
        setUpActionBar()

    }

    // setting up action bar
    private fun setUpActionBar() {
        binding.includedTap.back.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.includedTap.title.text = getString(R.string.add_new_location)
        binding.includedTap.menu.hide()
    }
}