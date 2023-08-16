package com.blueray.montak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blueray.montak.databinding.ActivityCreateNewAccountThirdBinding

class CreateNewAccountThirdActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCreateNewAccountThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityCreateNewAccountThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.continueBtn.setOnClickListener {
            startActivity(Intent(this,ChooseLocationActivity::class.java))
        }
        binding.previousBtn.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}