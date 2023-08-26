package com.blueray.montak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.startup.StartupLogger.e
import com.blueray.montak.adapters.ProductPagerAdapter
import com.blueray.montak.databinding.ActivityProductBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.ArrayList

class ProductActivity : BaseActivity() {

    private lateinit var binding : ActivityProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewPagerWithTapLayout()

        Log.e("ayham","this is product activity")

    }

    private fun setUpViewPagerWithTapLayout() {

        val list = arrayListOf("1","2","3","4","5","6","7","8","9")
        val adapter = ProductPagerAdapter(supportFragmentManager,lifecycle)
        val tabListTitle: MutableList<String> = ArrayList()
        list.forEach {
            adapter.addProductsList(list)
            tabListTitle.add(it)
        }
        binding.viewPager.adapter = adapter

        for (item in tabListTitle){
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(item))
        }

        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = tabListTitle[position]
        }.attach()

    }
}