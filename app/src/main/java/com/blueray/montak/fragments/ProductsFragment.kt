package com.blueray.montak.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.blueray.montak.R
import com.blueray.montak.adapters.ProductAdapter
import com.blueray.montak.databinding.FragmentProductsBinding
import com.blueray.montak.databinding.FragmentsearchBinding
import com.blueray.montak.helper.ViewUtils.hide
import com.blueray.montak.helper.ViewUtils.show
import com.blueray.montak.interfaces.OnProductListener
import com.blueray.montak.model.GetProudects
import com.blueray.montak.model.NetworkResults
import com.blueray.montak.model.ProudectModelItem
import com.blueray.montak.viewModel.appViewModel


class ProductsFragment : Fragment() {

    private lateinit var binding : FragmentProductsBinding
    private val viewmodel by viewModels<appViewModel>()

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
        val productList = arguments?.getParcelableArrayList<ProudectModelItem>("products")
        binding.productRv.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

        binding.productRv.adapter = productList?.let {
            ProductAdapter(requireContext(), it,object:OnProductListener{



                override fun addToCart(price: Double?, pid: Int?, quantity: String) {
                    TODO("Not yet implemented")
                }

                override fun addToFavourite(pid: Int) {
                    getProudectData()
                    viewmodel.addToFavourite(pid.toString())

                }

                override fun removeFromFavourite(favId: Int) {
                    TODO("Not yet implemented")
                }

                override fun showDetails(product: GetProudects) {
                    TODO("Not yet implemented")
                }




            })
        }
    }
    fun getRequestFav(){
        viewmodel.getAddToFavouriteMessage().observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResults.Success -> {
                    toast(result.data.status.msg)
                    viewmodel.getProudectsFav()
                }
                is NetworkResults.Error -> {
                    result.exception.printStackTrace()
                    toast(getString(R.string.error))
                }
            }
        }
    }

    fun toast(message: String) =
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    fun getProudectData() {
        viewmodel.getProudects().observe(viewLifecycleOwner) { result ->
            binding.progressBarSearch.hide()
            when (result) {
                is NetworkResults.Success -> {
                    if (result.data.status.status == 200) {
                        binding.productRv.layoutManager =
                            LinearLayoutManager(requireContext())
                        binding.productRv.adapter =
                            result.data.items?.let {
                                ProductAdapter(requireContext(),it, object : OnProductListener {
                                    override fun addToCart(
                                        price: Double?,
                                        pid: Int?,
                                        quantity: String
                                    ) {
                                    }

                                    override fun addToFavourite(pid: Int) {

                                        viewmodel.addToFavourite(pid.toString())

                                    }

                                    override fun removeFromFavourite(favId: Int) {
                                    }

                                    override fun showDetails(product: GetProudects) {
                                    }

                                })
                            }
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

                else -> {

                }
            }
        }
    }



}