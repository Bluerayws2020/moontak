package com.blueray.montak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blueray.montak.databinding.ActivityLoginActivtiyBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginActivtiyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginActivtiyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.continueBtn.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
        }

        binding.previosBtn.setOnClickListener {
            startActivity(Intent(this,CreateAccountActivity::class.java))
        }
    }
}