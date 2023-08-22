package com.example.aljabermall.adapters


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.blueray.montak.fragments.ProductsFragment
import com.blueray.montak.model.ProudectModelItem


class CategoryPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val productsList: MutableList<ArrayList<ProudectModelItem>> = ArrayList()

    fun addProductsList(products: List<ProudectModelItem>) {
        productsList.add(ArrayList(products))
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun createFragment(position: Int): Fragment {
        val productFragment = ProductsFragment()
        val bundle = Bundle()
        bundle.putParcelableArrayList("products", productsList[position])
        productFragment.arguments = bundle
        return productFragment
    }
}