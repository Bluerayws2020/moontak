package com.blueray.montak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import com.blueray.montak.CreateAccountActivity.Companion.otherSec
import com.blueray.montak.CreateAccountActivity.Companion.sectoreId
import com.blueray.montak.databinding.ActivityCreateNewAccountSecondBinding
import com.blueray.montak.databinding.ActivityCreateNewAccountThirdBinding
import com.blueray.montak.helper.ViewUtils.hide
import com.blueray.montak.helper.ViewUtils.show
import com.blueray.montak.model.MSector
import com.blueray.montak.model.NetworkResults
import com.blueray.montak.model.SectorsItem
import com.blueray.montak.viewModel.appViewModel
import com.example.aljabermall.helpers.HelperUtils
import retrofit2.HttpException

class CreateNewAccountThirdActivity : BaseActivity() {
    private val viewmodel by viewModels<appViewModel>()
    private lateinit var binding: ActivityCreateNewAccountThirdBinding
    private var sectoreList: ArrayList<MSector> = ArrayList()
    private var subSectoreList: ArrayList<MSector> = ArrayList()
    var idSector = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNewAccountThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ... [rest of your initialization code]

        initSectorsObserver()
        initSubSectorsObserver()
        viewmodel.retriveSectore()

        otherSec = binding.otherSec.text.toString()
        binding.continueBtn.setOnClickListener {
            val intentHome = Intent(this, OtpActivity::class.java)
            startActivity(intentHome)
        }

        subSectoreList.add(MSector("0","غير ذلك"))




        binding.sector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {


                // You can also populate spinner2 data based on the item selected in spinner1

                viewmodel.retriveSubSectore(sectoreList[position].tid)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                binding.subSectore.visibility = View.GONE
            }

        }


        binding.subSectore.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                if (subSectoreList[position].tid == "0"){
                    binding.subSectore.hide()
                    binding.otherSec.show()
                }else {
                    binding.subSectore.show()
                    binding.otherSec.hide()

                }


                if (subSectoreList[position].tid == "0") {


                    CreateAccountActivity.otherSec = binding.otherSec.text.toString()

                }else {
                    CreateAccountActivity.sectoreId =  binding.otherSec.text.toString()
                }

                // You can also populate spinner2 data based on the item selected in spinner1

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                binding.subSectore.visibility = View.GONE
            }

        }



    }

    private fun initSectorsObserver() {
        viewmodel.getSectore().observe(this) { result ->
            when (result) {
                is NetworkResults.Success -> {
                    hideProgress()
                    if (result.data.status.status == 200) {
                        sectoreList = result.data.user as ArrayList<MSector>
                        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sectoreList.map { it.name })
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        binding.sector.adapter = adapter
                    } else {
                        showMessage(result.data.status.msg)
                    }
                }
                else -> hideProgress()
            }
        }
    }

    private fun initSubSectorsObserver() {
        viewmodel.getSubSectore().observe(this) { result ->
            when (result) {
                is NetworkResults.Success -> {
                    hideProgress()
                    if (result.data.status.status == 200) {
                        subSectoreList = result.data.user as ArrayList<MSector>
                        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, subSectoreList.map { it.name })
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        binding.subSectore.adapter = adapter
                    } else {
                        showMessage(result.data.status.msg)
                    }
                }
                else -> hideProgress()
            }
        }
    }
//1-4
    private fun handleError(exception: Throwable) {
        hideProgress()
        if (exception is HttpException) {
            showMessage(exception.message())
        } else {
            showMessage(getString(R.string.error))
        }
    }

    private fun showMessage(message: String?) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

    private fun hideProgress() {
        binding.continueBtn.show()
        binding.progressBar.hide()
    }

    private fun showProgress() {
        binding.continueBtn.hide()
        binding.progressBar.show()
    }
}
// new vid on cart depend on item