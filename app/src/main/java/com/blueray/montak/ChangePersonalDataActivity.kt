package com.blueray.montak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blueray.montak.databinding.ActivityChnagePersonalDataBinding
import com.blueray.montak.helper.ViewUtils.hide

class ChangePersonalDataActivity : BaseActivity() {

    private lateinit var binding : ActivityChnagePersonalDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChnagePersonalDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setup App bar
        setUpActionBar()
    }
    // setting up action bar
    private fun setUpActionBar() {
        binding.includedTap.back.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.includedTap.title.text = getString(R.string.personal_data)
        binding.includedTap.menu.hide()
    }
}