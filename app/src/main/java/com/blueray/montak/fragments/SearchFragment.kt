package com.blueray.montak.fragments

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.blueray.montak.R
import com.blueray.montak.adapters.ProductAdapter
import com.blueray.montak.databinding.FragmentsearchBinding
import com.blueray.montak.helper.ViewUtils.hide
import com.blueray.montak.helper.ViewUtils.show
import com.blueray.montak.interfaces.OnProductListener
import com.blueray.montak.model.GetProudects
import com.blueray.montak.model.NetworkResults
import com.blueray.montak.viewModel.appViewModel
import com.example.aljabermall.helpers.HelperUtils


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentsearchBinding
    private val viewmodel by viewModels<appViewModel>()

    override fun onCreateView(


        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentsearchBinding.inflate(layoutInflater)



        getProudectData()
binding.searchsss.setOnClickListener{
    searchProducts(binding.search.text.toString())

}
        return binding.root

    }


    private fun searchProducts(searchTerm: String) {
        HelperUtils.hideKeyBoard(context as Activity)
        if (searchTerm.isNotEmpty()) {
            binding.messageSearch.hide()
            binding.progressBarSearch.show()
            viewmodel.retriveProudects("",searchTerm)
        } else
            toast(getString(R.string.search_empty))
    }

    fun toast(message: String) =
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    fun getProudectData() {
        viewmodel.getProudects().observe(viewLifecycleOwner) { result ->
            binding.progressBarSearch.hide()
            when (result) {
                is NetworkResults.Success -> {
                    if (result.data.status.status == 200) {
                        binding.productsSearchRecycler.layoutManager =
                            LinearLayoutManager(requireContext())

                        binding.productsSearchRecycler.adapter =
                            result.data.items?.let {
                                ProductAdapter(
                                    requireContext(), it,object:OnProductListener{
                                        override fun addToCart(
                                            price: Double?,
                                            pid: Int?,
                                            quantity: String
                                        ) {
                                            TODO("Not yet implemented")
                                        }

                                        override fun addToFavourite(pid: Int) {
                                            TODO("Not yet implemented")
                                        }

                                        override fun removeFromFavourite(favId: Int) {
                                            TODO("Not yet implemented")
                                        }

                                        override fun showDetails(product: GetProudects) {
                                            TODO("Not yet implemented")
                                        }
                                    })
                            }

//                        binding.messageSearch.apply {
//                            show()
//                            text = result.data.status.msg
//                        }
                    } else {
                        binding.messageSearch.apply {
                            show()
                            text = result.data.status.msg
                        }
                    }
                }

                is NetworkResults.Error -> {
                    result.exception.printStackTrace()
                    binding.messageSearch.apply {
                        show()
                        text = getString(R.string.error)
                    }
                }
            }
        }
    }




}