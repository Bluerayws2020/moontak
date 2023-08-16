package com.blueray.montak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony.Sms.Intents
import com.blueray.montak.databinding.ActivityChooseLanguageBinding

class ChooseLanguageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChooseLanguageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.arLanguageBtn.setOnClickListener {
            binding.arCheckBox.isChecked = true
            binding.enCheckBox.isChecked = false
        }


        binding.enLanguageBtn.setOnClickListener {
            binding.arCheckBox.isChecked = false
            binding.enCheckBox.isChecked = true

        }

        binding.continueBtn.setOnClickListener {
            startActivity(Intent(this,OnBoardingActivity::class.java))
        }

    }
}