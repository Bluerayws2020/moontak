package com.blueray.montak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony.Sms.Intents
import androidx.lifecycle.lifecycleScope
import com.blueray.montak.databinding.ActivityChooseLanguageBinding
import com.example.aljabermall.helpers.HelperUtils.setDefaultLanguage
import com.example.aljabermall.helpers.HelperUtils.setLang
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChooseLanguageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChooseLanguageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.arLanguageBtn.setOnClickListener {
            binding.arCheckBox.isChecked = true
            binding.enCheckBox.isChecked = false
            changeLanguage("ar")
        }


        binding.enLanguageBtn.setOnClickListener {
            binding.arCheckBox.isChecked = false
            binding.enCheckBox.isChecked = true
            changeLanguage("en")
        }

        binding.continueBtn.setOnClickListener {
            startActivity(Intent(this,OnBoardingActivity::class.java))
        }

    }

    private fun changeLanguage(lang:String){
        lifecycleScope.launch {
            delay(300)
            setDefaultLanguage(this@ChooseLanguageActivity,lang)
            setLang(this@ChooseLanguageActivity,lang)
            startActivity(Intent(this@ChooseLanguageActivity,ChooseLanguageActivity::class.java))
            finish()

        }
    }

}