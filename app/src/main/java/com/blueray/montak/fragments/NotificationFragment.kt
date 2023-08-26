package com.blueray.montak.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blueray.montak.HomeActivity
import com.blueray.montak.R
import com.blueray.montak.databinding.FragmentNotficationBinding
import com.blueray.montak.helper.ViewUtils.hide


class NotificationFragment : Fragment() {

    private lateinit var binding : FragmentNotficationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNotficationBinding.inflate(layoutInflater)

        // setup App Bar Item
        setUpActionBar()

        return binding.root
    }
    // setting up action bar
    private fun setUpActionBar() {
        binding.includedTap.back.hide()
//            .setOnClickListener {
//            (activity as HomeActivity).onBackPressedDispatcher.onBackPressed()
//        }
        binding.includedTap.title.text =getString(R.string.notifications)
        binding.includedTap.menu.setOnClickListener {
            (activity as HomeActivity).openDrawer()
        }
    }

}