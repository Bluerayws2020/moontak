package com.blueray.montak.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.blueray.montak.R
import com.blueray.montak.adapters.ProductAdapter
import com.blueray.montak.databinding.FragmentProductInfoBottomSheetBinding
import com.blueray.montak.databinding.FragmentProductsBinding
import com.blueray.montak.databinding.FragmentsearchBinding
import com.blueray.montak.databinding.ProductDeatailsBottomSheetBinding
import com.blueray.montak.helper.ViewUtils.hide
import com.blueray.montak.helper.ViewUtils.show
import com.blueray.montak.interfaces.OnProductListener
import com.blueray.montak.model.GetProudects
import com.blueray.montak.model.NetworkResults
import com.blueray.montak.model.ProudectModelItem
import com.blueray.montak.viewModel.appViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog


class ProductsFragment : Fragment() {

    private lateinit var binding : FragmentProductsBinding
    private val viewmodel by viewModels<appViewModel>()
    private lateinit var  dialog  : BottomSheetDialog

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
//      initialize Bottom sheet Object
        dialog  = BottomSheetDialog(requireActivity())
        val productList = arguments?.getParcelableArrayList<ProudectModelItem>("products")
        binding.productRv.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

        binding.productRv.adapter = productList?.let {
            ProductAdapter(requireContext(), it,object:OnProductListener{



                override fun addToCart(price: Double?, pid: Int?, quantity: String) {
                    // todo not implemented yet
                }

                override fun addToFavourite(pid: Int) {
                    getProudectData()
                    viewmodel.addToFavourite(pid.toString())

                }

                override fun removeFromFavourite(favId: Int) {
//                    TODO("Not yet implemented")
                }

                override fun showDetails(product: GetProudects) {
                    showBottomSheet()
                }




            })
        }
    }

    private fun showBottomSheet() {


        if(!dialog.isShowing ){

            // initialize binding for bottom sheet
            val botBinding = ProductDeatailsBottomSheetBinding.inflate(layoutInflater)

            // Set rounded corner drawable as background
            botBinding.root.background = ContextCompat.getDrawable(requireContext(), R.drawable.buttom_sheet_back)

            // address viewBinding to the bottomSheet dialog
            dialog.setContentView(botBinding.root)

            dialog.show()

            // bind data
            botBinding.addToCartBtn.setOnClickListener {
                // todo implement add to cart api
            }


            // plus btn imp
            botBinding.plus.setOnClickListener {
    //            add one item to the cart
                var count = botBinding.counter.text.toString().toInt()
                count +=1
                botBinding.counter.text = count.toString()
            }


    //        minus imp
            botBinding.minus.setOnClickListener {
    //            if count = 0 then do not decrement
                var count = botBinding.counter.text.toString().toInt()
                if(count != 0){
                    count -=1
                    botBinding.counter.text = count.toString()
                }

            }

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