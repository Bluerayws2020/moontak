package com.blueray.montak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class OtpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        // change this but I think this page is Implemented by jaber
        findViewById<Button>(R.id.continueBtn).setOnClickListener {  startActivity(Intent(this,HomeActivity::class.java))}

    }
}