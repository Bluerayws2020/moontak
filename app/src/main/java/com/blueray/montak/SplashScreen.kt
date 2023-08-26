package com.blueray.montak

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.lifecycleScope
import com.blueray.montak.databinding.ActivityOnBoardingBinding
import com.example.aljabermall.helpers.HelperUtils
import com.example.aljabermall.helpers.HelperUtils.setLang
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreen : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        // change lang
        changeLanguage("ar")

        lifecycleScope.launch {
           delay(2000)
            if (HelperUtils.getUID(this@SplashScreen) != "0") {
                openUser()

            } else
                openHome()

        }


    }
    private fun openHome() {
        val intentHome = Intent(this, HomeActivity::class.java)
        startActivity(intentHome)
        finish()
    }

    private fun openUser() {
        val intentHome = Intent(this, HomeActivity::class.java)
        startActivity(intentHome)
        finish()
    }
    private fun changeLanguage(lang:String){
        HelperUtils.setDefaultLanguage(this@SplashScreen, lang)
        setLang(this@SplashScreen,lang)
    }
}