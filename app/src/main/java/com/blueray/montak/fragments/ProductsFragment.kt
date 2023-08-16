package com.blueray.montak.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.blueray.montak.R
import com.blueray.montak.adapters.ProductAdapter
import com.blueray.montak.databinding.FragmentProductsBinding


class ProductsFragment : Fragment() {

    private lateinit var binding : FragmentProductsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding  = FragmentProductsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.productRv.layoutManager = LinearLayoutManager(requireContext())
        binding.productRv.adapter = ProductAdapter(listOf())
    }

}