package com.blueray.montak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blueray.montak.databinding.ActivityCreateNewAccountSecondBinding

class CreateNewAccountSecondActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCreateNewAccountSecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNewAccountSecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.continueBtn.setOnClickListener {
            startActivity(Intent(this,CreateNewAccountThirdActivity::class.java))
        }

    }
}