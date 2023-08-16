package com.blueray.montak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.blueray.montak.databinding.ActivityHomeBinding
import com.blueray.montak.fragments.ProfileFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding :ActivityHomeBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        NavigationUI.setupWithNavController(binding.bottomNavBar, navController)
        NavigationUI.setupWithNavController(binding.navDrawer, navController)

        binding.myOrdersFab.setOnClickListener {
            startActivity(Intent(this,CartActivity::class.java))
        }
        // set Up drawer Navigation
        setUpDrawerNavigation()

    }

    // add drawer Navigation
    private fun setUpDrawerNavigation() {
        binding.navDrawer.setNavigationItemSelectedListener {
                menuItem->
            when(menuItem.itemId){
                R.id.home->{
                    navController.navigate(R.id.home)
                    true
                }
                R.id.search->{
                    startActivity(Intent(this@HomeActivity,ProductActivity::class.java))
                    true
                }
                R.id.favorite->{
                    true
                }
                R.id.notifications->{
                    true
                }
                R.id.profile ->{
                    navController.navigate(R.id.profile)
                    true
                }
                R.id.aboutUs ->{

                    true
                }
                R.id.privacyPolicy ->{
                    true
                }
                R.id.customerService->{
                    true
                }
                R.id.changeLanguage->{
                    startActivity(Intent(this@HomeActivity,ChooseLanguageActivity::class.java))
                    true
                }
                R.id.exit->{
                    finish()
                    true
                }
                else ->{
                    false
                }
            }
        }
    }
}