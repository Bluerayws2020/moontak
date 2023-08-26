package com.blueray.montak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blueray.montak.databinding.ActivityClockTestBinding
import ir.samanjafari.easycountdowntimer.CountDownInterface
import java.util.Locale

class ClockTest : BaseActivity() {

    private lateinit var binding : ActivityClockTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClockTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        

        binding.easyCountDownTextview.setTime(1,1,1,1)
        binding.easyCountDownTextview.setOnTick(object :CountDownInterface{
            override fun onTick(time: Long) {
            }

            override fun onFinish() {

            }
        })
        binding.easyCountDownTextview.startTimer()


    }
}