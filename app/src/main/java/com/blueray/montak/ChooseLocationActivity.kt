package com.blueray.montak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blueray.montak.databinding.ActivityChooseLocationBinding

class ChooseLocationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityChooseLocationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mapIcon.setOnClickListener {
            startActivity(Intent(this,MapActivity::class.java))
        }

        binding.continueBtn.setOnClickListener {
            startActivity(Intent(this,OtpActivity::class.java))
        }

        binding.previosBtn.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

    }
}