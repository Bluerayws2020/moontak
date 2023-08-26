package com.blueray.montak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import com.blueray.montak.databinding.ActivityLoginActivtiyBinding
import com.blueray.montak.databinding.ActivityOtpBinding
import com.blueray.montak.helper.ViewUtils.hide
import com.blueray.montak.helper.ViewUtils.show
import com.blueray.montak.model.NetworkResults
import com.blueray.montak.model.User
import com.blueray.montak.viewModel.appViewModel
import com.example.aljabermall.helpers.HelperUtils
import retrofit2.HttpException

class OtpActivity : BaseActivity() {
    private val viewmodel by viewModels<appViewModel>()
    private lateinit var binding: ActivityOtpBinding
var otpNum = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        HelperUtils.setDefaultLanguage(this,"ar")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val phone = intent.extras?.getString(HelperUtils.PHONENUMEBR)
        val flag = intent.extras?.getString(HelperUtils.FlagRegestr)

        if (flag == "1"){
            binding.phoneNumberET.text = CreateAccountActivity.phone.toString()

        }else {
            binding.phoneNumberET.text = phone ?: "0776"

        }


        binding.otpView.setOtpCompletionListener{
            showProgress()
            viewmodel.retrivelogin(phone.toString(),it)
            otpNum = it
            showProgress()

        }
        binding.continueBtn.setOnClickListener {
            viewmodel.retrivelogin(phone.toString(),otpNum)
showProgress()
        }
getData()

    }

    fun getData(){


        viewmodel.getLogin().observe(this) { result ->
            when (result) {
                is NetworkResults.Success -> {
                    hideProgress()
                    if (result.data.status.status == 200) {

                        val intentHome = Intent(this, HomeActivity::class.java)



                        startActivity(intentHome)
                        showMessage(result.data.status.msg)
                        result.data.user?.let { saveUserData(it) }

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
                        hideProgress()
                        showMessage(getString(R.string.error))
                }

                else -> {
                    hideProgress()
                }
            }
        }
    }
    private fun saveUserData(user: User) {
        val sharedPreferences = getSharedPreferences(HelperUtils.SHARED_PREF, MODE_PRIVATE)
        sharedPreferences.edit().apply {
            putString("uid", user.id.toString())
            putString("phone", user.phone)
            putString("user_type", user.user_type)
        }.apply()
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