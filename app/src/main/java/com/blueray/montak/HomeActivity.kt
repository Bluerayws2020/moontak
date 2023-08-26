package com.blueray.montak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.blueray.montak.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity() {

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



        binding.bottomNavBar.setOnItemSelectedListener {
                item ->
            when(item.itemId){
                R.id.home ->{
                    navController.navigate(R.id.home)
                    true
                }
                R.id.favorite->{
                    navController.navigate(R.id.favFragment)
                    true
                }
                R.id.notifications->{
                    navController.navigate(R.id.notficationFragment)
                    true
                }
                R.id.search ->{
                    navController.navigate(R.id.searchFragment)
                    true

                }
                else ->{
                    false
                }
            }
        }


    }

    // add drawer Navigation
    private fun setUpDrawerNavigation() {
        binding.navDrawer.setNavigationItemSelectedListener {
                menuItem->
            when(menuItem.itemId){
                R.id.home->{
                    navController.navigate(R.id.home)
                    closeDrawer()
                    true
                }
                R.id.search->{
                    navController.navigate(R.id.searchFragment)
                    closeDrawer()
                    true
                }
                R.id.favorite->{
                    navController.navigate(R.id.favFragment)
                    closeDrawer()
                    true
                }
                R.id.notifications->{
                    navController.navigate(R.id.notficationFragment)
                    closeDrawer()
                    true
                }
                R.id.profile ->{
                    startActivity(Intent(this,ProfileFragment::class.java).apply {
                        // todo add extras
                    })
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
    fun openDrawer(){
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }
    fun closeDrawer(){
        binding.drawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START))
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }
}