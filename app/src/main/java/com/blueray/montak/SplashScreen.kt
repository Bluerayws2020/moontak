package com.blueray.montak

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // delay 3000 ms and Launch on Boarding Activity
        lifecycleScope.launch {
            delay(3000)
            startActivity(Intent(this@SplashScreen,ChooseLanguageActivity::class.java))
        }
    }
}