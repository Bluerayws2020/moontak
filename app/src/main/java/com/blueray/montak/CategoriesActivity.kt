package com.blueray.montak

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.blueray.montak.databinding.ActivityProductBinding
import com.blueray.montak.databinding.FragmentCategoriesBinding
import com.blueray.montak.helper.ViewUtils.hide
import com.blueray.montak.model.CategoriesItems
import com.blueray.montak.model.NetworkResults
import com.blueray.montak.viewModel.appViewModel
import com.example.aljabermall.adapters.CategoryPagerAdapter
import com.example.aljabermall.helpers.HelperUtils
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.ArrayList
import java.util.Locale

class CategoriesActivity : AppCompatActivity() {
companion object{
    val CATE_ID = "CATE_ID"
}
    private lateinit var binding: ActivityProductBinding
    private val productVM by viewModels<appViewModel>()
    private var cateId: Int? = null
    private var language = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        supportActionBar?.title = getString(R.string.categories)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        cateId = intent.extras?.getInt(CATE_ID)
Log.d("cateIdcateId",cateId.toString())
        productVM.getProductsCate().observe(this) { result ->
            when (result) {
                is NetworkResults.Success -> {
                    if (result.data.status.status == 200) {
                        setupProductsPager(result.data.categories_with_items)
                    } else {
//                        binding.messageCategories.show()
                    }
                }
                is NetworkResults.Error -> {
                    result.exception.printStackTrace()
//                    binding.messageCategories.show()
                }
            }
//            binding.progressBarTab.hide()
        }

        productVM.retrieveProductCategories()

        language = HelperUtils.getLang(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupProductsPager(list: List<CategoriesItems?>) {
        val categoryPagerAdapter = CategoryPagerAdapter(supportFragmentManager, lifecycle)
        val tabListTitle: MutableList<String> = ArrayList()
        list.forEach { cate ->
            cate?.let {
                if (language == "ar")
                    tabListTitle.add(it.category_name_ar)

                categoryPagerAdapter.addProductsList(it.items)
            }
        }

        binding.viewPager.adapter = categoryPagerAdapter

        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = tabListTitle[position]
        }.attach()


//        val marginValue = resources.getDimensionPixelSize(R.dimen.tab_margin) // This will fetch 8dp margin

        for (i in 0 until binding.tabLayout.tabCount) {
            val tab = binding.tabLayout.getTabAt(i)

//            val layoutParams = (tab?.view?.layoutParams as LinearLayout.LayoutParams).apply {
//                if (i != 0) { // don't add start margin for the first item
//                    marginStart = marginValue
//                }
//                if (i != binding.tabLayout.tabCount - 1) { // don't add end margin for the last item
//                    marginEnd = marginValue
//                }
//            }

//            tab?.view?.layoutParams = layoutParams

            if (i == binding.tabLayout.selectedTabPosition) {
                tab?.view?.setBackgroundResource(R.drawable.tab_selected_background)
            } else {
                tab?.view?.setBackgroundResource(R.drawable.unselected)
            }
        }

        for (i in 0 until binding.tabLayout.tabCount) {
            val tab = binding.tabLayout.getTabAt(i)
            if (i == binding.tabLayout.selectedTabPosition) {
                tab?.view?.setBackgroundResource(R.drawable.tab_selected_background)
            } else {
                tab?.view?.setBackgroundResource(R.drawable.unselected)
            }
        }


        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tab.view.setBackgroundResource(R.drawable.tab_selected_background)

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.view.setBackgroundResource(R.drawable.unselected)

            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        binding.tabLayout.visibility = View.VISIBLE

        val pos = list.indexOfFirst {
            it?.category_id == cateId
        }

        binding.viewPager.setCurrentItem(pos, false)
    }


}