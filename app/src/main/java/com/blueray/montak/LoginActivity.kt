package com.blueray.montak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import com.blueray.montak.databinding.ActivityLoginActivtiyBinding
import com.blueray.montak.helper.ViewUtils.hide
import com.blueray.montak.helper.ViewUtils.show
import com.blueray.montak.model.NetworkResults
import com.blueray.montak.viewModel.appViewModel
import com.example.aljabermall.helpers.HelperUtils
import com.example.aljabermall.helpers.HelperUtils.PHONENUMEBR
import retrofit2.HttpException

class LoginActivity : AppCompatActivity() {
    private val viewmodel by viewModels<appViewModel>()
    private lateinit var binding: ActivityLoginActivtiyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginActivtiyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        HelperUtils.setDefaultLanguage(this,"ar")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


getData()
hideProgress()

        binding.continueBtn.setOnClickListener {

            if (binding.phoneNumberET.text.isNullOrEmpty()){
showMessage("you cant send empty phone")
            }

            else {
                showProgress()

                viewmodel.retrivelogin(binding.phoneNumberET.text.toString(), "")
            }

        }
binding.continueBtn.setOnClickListener {
    val intentHome = Intent(this, CreateAccountActivity::class.java)
    startActivity(intentHome)

}



    }

    fun getData(){

        hideProgress()

        viewmodel.getLogin().observe(this) { result ->
            when (result) {
                is NetworkResults.Success -> {
                    if (result.data.status.status == 200) {

                            val intentHome = Intent(this, OtpActivity::class.java)
                            val phone = binding.phoneNumberET.text

                            intentHome.putExtra(PHONENUMEBR, phone.toString());


                            startActivity(intentHome)
                        showMessage(result.data.status.msg)

                    } else {
                        showMessage(result.data.status.msg)
                        hideProgress()
                    }
                }
                is NetworkResults.Error -> {
                    hideProgress()

                    result.exception.printStackTrace()
                    hideProgress()
                    if (result.exception is HttpException)
                        showMessage(result.exception.message())
                    else
                        showMessage(getString(R.string.error))
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
        binding.createNewAccountBtn.show()
        binding.progressBar.hide()
    }
    private fun showProgress() {
        binding.continueBtn.hide()
        binding.progressBar.show()
    }

}