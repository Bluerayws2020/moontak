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
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.blueray.montak.databinding.ActivityCartBinding
import com.blueray.montak.databinding.ActivityProductBinding
import com.blueray.montak.databinding.FragmentCategoriesBinding
import com.blueray.montak.databinding.SelectedCatViewBinding
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

class CategoriesActivity : BaseActivity() {
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

        setUpActionBar()

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
            // set custom layout for all the tab layout and st the text
            val customLayoutBinding = SelectedCatViewBinding.inflate(layoutInflater)
            tab.customView = customLayoutBinding.root
            (tab.customView as CardView).findViewById<TextView>(R.id.title).text = tabListTitle[position]

        }.attach()

        // at the first the selected tab dose not invoke the on tab selected function so this is the need for this code
        selectItem()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                (tab.customView as CardView).background.setTint(ContextCompat.getColor(this@CategoriesActivity,R.color.blue_500))
                (tab.customView as CardView).findViewById<TextView>(R.id.title).setTextColor(ContextCompat.getColor(this@CategoriesActivity,R.color.white))

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                (tab.customView as CardView).background.setTint(ContextCompat.getColor(this@CategoriesActivity,R.color.tab_grey))
                (tab.customView as CardView).findViewById<TextView>(R.id.title).setTextColor(ContextCompat.getColor(this@CategoriesActivity,R.color.textGrey47))

            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        binding.tabLayout.visibility = View.VISIBLE

        val pos = list.indexOfFirst {
            it?.category_id == cateId
        }

        binding.viewPager.setCurrentItem(pos, false)
    }

    private fun selectItem() {
//        get Selected Tap
        val tab = binding.tabLayout.getTabAt(binding.tabLayout.selectedTabPosition)
        (tab?.customView as CardView).background.setTint(
            ContextCompat.getColor(
                this,
                R.color.blue_500
            )
        )
        (tab.customView as CardView).findViewById<TextView>(R.id.title)
            .setTextColor(ContextCompat.getColor(this, R.color.white))
    }

// setting up action bar
    private fun setUpActionBar() {
        binding.includedTap.back.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.includedTap.title.text = getString(R.string.categories)
        binding.includedTap.menu.hide()
    }


}