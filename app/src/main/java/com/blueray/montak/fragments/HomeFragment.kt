package com.blueray.montak.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.blueray.montak.CartActivity
import com.blueray.montak.CategoriesActivity
import com.blueray.montak.CategoriesActivity.Companion.CATE_ID
import com.blueray.montak.HomeActivity
import com.blueray.montak.R
import com.blueray.montak.adapters.HomeCategoryAdapter
import com.blueray.montak.adapters.HomeDealsAdapter
import com.blueray.montak.adapters.HomeSliderAdapter
import com.blueray.montak.databinding.FragmentHomeBinding
import com.blueray.montak.helper.ViewUtils.hide
import com.blueray.montak.helper.ViewUtils.show
import com.blueray.montak.interfaces.onItemClikc
import com.blueray.montak.model.NetworkResults
import com.blueray.montak.viewModel.appViewModel

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var dealsAdapter: HomeDealsAdapter
    private lateinit var categoryAdapter:HomeCategoryAdapter
    private lateinit var homeSliderAdapter: HomeSliderAdapter
    private val viewmodel by viewModels<appViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        dealsAdapter = HomeDealsAdapter(listOf())
        homeSliderAdapter =  HomeSliderAdapter(requireContext(),listOf())

        // nav drawer open and close
        binding.includedTap.menuButton.setOnClickListener {
            (requireActivity() as HomeActivity).openDrawer()
        }
        // navigate to cart
        binding.includedTap.cartLabel.setOnClickListener {
            startActivity(Intent(activity,CartActivity::class.java).apply {
                // todo add extras
            })
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.retriveCatLive()

        // set up recyclers
        setUpDealsRec()
        setUpCategoryRec()

        viewmodel.retriveCatLive()
        getCategorys()

    }

    // set up deals recycler
    private fun setUpDealsRec(){
        val lm =LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.dealsRec.layoutManager = lm
        binding.dealsRec.adapter = dealsAdapter
    }
    // set up deals recycler
    private fun setUpCategoryRec(){
        val lm =LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.suboffer.layoutManager = lm
        binding.suboffer.adapter = homeSliderAdapter

    }


private fun getCategorys(){
    viewmodel.getCats().observe(viewLifecycleOwner) { result ->
        binding.progressBarCate.hide()
//        binding.swipeToRefresh.isRefreshing = false
        when (result) {
            is NetworkResults.Success -> {
                if (result.data.status.status == 200) {
                    val lm =GridLayoutManager(requireContext(),2,GridLayoutManager.HORIZONTAL,false)
                    binding.categoriesRv.layoutManager = lm
                    binding.categoriesRv.adapter =
                        result.data.items?.let { HomeCategoryAdapter(it, object :onItemClikc{
                            override fun onItemClick(pos: Int) {

                                Toast.makeText(requireContext(),"Jaber $pos",Toast.LENGTH_LONG).show()

                                val intentCategories = Intent(context, CategoriesActivity::class.java)
                                val bundle = Bundle()
                                bundle.putInt(CATE_ID, result.data.items[pos].id)
                                intentCategories.putExtras(bundle)
                                startActivity(intentCategories)


                            }
                        }) }

                } else {
                    binding.messageCate.show()
                    binding.messageCate.text = result.data.status.msg
                }


            }
            is NetworkResults.Error -> {
                result.exception.printStackTrace()
                Log.d("Stringss",result.exception.toString())
                binding.messageCate.show()
            }

            else -> {}
        }
    }
    }







}