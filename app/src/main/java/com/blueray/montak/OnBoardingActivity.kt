package com.blueray.montak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.blueray.montak.adapters.OnBoardingAdapter
import com.blueray.montak.databinding.ActivityOnBoardingBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding : ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set viewpager with indicator
        binding.viewPager.adapter = OnBoardingAdapter(supportFragmentManager,lifecycle)
        binding.indicator.setViewPager(binding.viewPager)

        // add fake swipe
        binding.next.setOnClickListener {
            binding.viewPager.setCurrentItem(binding.viewPager.currentItem+1, true)
        }

    }
}