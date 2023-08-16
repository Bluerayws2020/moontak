package com.blueray.montak.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.blueray.montak.R
import com.blueray.montak.adapters.HomeCategoryAdapter
import com.blueray.montak.adapters.HomeDealsAdapter
import com.blueray.montak.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var dealsAdapter: HomeDealsAdapter
    private lateinit var categoryAdapter:HomeCategoryAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        dealsAdapter = HomeDealsAdapter(listOf())
        categoryAdapter = HomeCategoryAdapter(listOf())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set up recyclers
        setUpDealsRec()
        setUpCategoryRec()

    }

    // set up deals recycler
    private fun setUpDealsRec(){
        val lm =LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.dealsRec.layoutManager = lm
        binding.dealsRec.adapter = dealsAdapter
    }
    // set up deals recycler
    private fun setUpCategoryRec(){
        val lm =GridLayoutManager(requireContext(),2,GridLayoutManager.HORIZONTAL,false)
        binding.categoriesRv.layoutManager = lm
        binding.categoriesRv.adapter = categoryAdapter
    }




}