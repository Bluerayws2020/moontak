package com.blueray.montak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blueray.montak.databinding.ActivityChooseLocationBinding
import com.blueray.montak.databinding.ActivityInnerAddNewLocationsBinding
import com.blueray.montak.helper.ViewUtils.hide

class InnerAddNewLocationsActivity : BaseActivity() {

    private lateinit var binding : ActivityInnerAddNewLocationsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInnerAddNewLocationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set up action bar
        setUpActionBar()

        // go to map activity
        binding.mapIcon.setOnClickListener {
            startActivity(Intent(this,MapActivity::class.java).apply {
                // todo add extras
            })
        }

        binding.continueBtn.setOnClickListener {
            startActivity(Intent(this,OtpActivity::class.java).apply {
                // todo add extras
            })
        }

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