package com.blueray.montak

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blueray.montak.databinding.FragmentProfileBinding
import com.blueray.montak.helper.ViewUtils.hide

class ProfileFragment : BaseActivity() {

    private lateinit var binding : FragmentProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // setup action bar
        setUpActionBar()

        binding.personalDataBtn.setOnClickListener {
            startActivity(Intent(this,ChangePersonalDataActivity::class.java).apply {
                // todo add extras
            })
        }
        binding.changeNumber.setOnClickListener {
            startActivity(Intent(this,ChangeNumberActivity::class.java).apply {
                // todo add extras
            })
        }
        binding.sectorBtn.setOnClickListener {
            // todo add sector activity
            // todo no design is included in the design xd file
        }
        binding.addressBtn.setOnClickListener {
            startActivity((Intent(this,AddLocationActivity::class.java)))
        }

    }

    // setting up action bar
    private fun setUpActionBar() {
        binding.includedTap.back.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.includedTap.title.text =getString(R.string.my_profile)

        binding.includedTap.menu.hide()

    }


}