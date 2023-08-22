package com.blueray.montak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import com.blueray.montak.databinding.ActivityCreateAccountBinding
import com.blueray.montak.databinding.ActivityLoginActivtiyBinding
import com.blueray.montak.helper.ViewUtils.hide
import com.blueray.montak.helper.ViewUtils.show
import com.blueray.montak.model.NetworkResults
import com.blueray.montak.viewModel.appViewModel
import com.example.aljabermall.helpers.HelperUtils
import retrofit2.HttpException

class CreateAccountActivity : AppCompatActivity() {
    companion object{
        var phone:String = ""
        var name:String = ""
        var companyName:String = ""
        var emial:String = ""
        var sectoreId :String = ""
        var otherSec :String = ""

    }
    private val viewmodel by viewModels<appViewModel>()
    private lateinit var binding: ActivityCreateAccountBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        var lang  = HelperUtils.getLang(this)
        HelperUtils.setDefaultLanguage(this,lang)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



getData()

        binding.continueBtn.setOnClickListener {

            if (binding.phoneNumberET.text.isNullOrEmpty()){
                showMessage("you cant send empty phone")
            }

            else {
                showProgress()

                viewmodel.checkPhoen(binding.phoneNumberET.text.toString())
            }

        }



    }


    fun getData(){


        viewmodel.getCheckPhone().observe(this) { result ->
            when (result) {
                is NetworkResults.Success -> {
                    hideProgress()
                    if (result.data.status.status == 200) {
                        showMessage(result.data.status.msg)

                        val intentHome = Intent(this, CreateNewAccountSecondActivity::class.java)


                        startActivity(intentHome)



                        CreateAccountActivity.phone = binding.phoneNumberET.text.toString()
                    } else {
                        showMessage(result.data.status.msg.toString())
                        hideProgress()
                    }
                }
                is NetworkResults.Error -> {

                    result.exception.printStackTrace()
                    hideProgress()


                }

                else -> {
                    hideProgress()

                }
            }
        }
    }


    private fun showMessage(message: String?) =
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

    private fun hideProgress() {
        binding.continueBtn.show()
        binding.progressBar.hide()
    }
    private fun showProgress() {
        binding.continueBtn.hide()
        binding.progressBar.show()
    }
}