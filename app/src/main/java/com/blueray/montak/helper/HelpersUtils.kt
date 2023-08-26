package com.example.aljabermall.helpers

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.provider.Settings
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.blueray.montak.MainActivity
import com.blueray.montak.R
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.language.bm.Languages
import java.util.*

object HelperUtils {
//    const val BASE_URL_EN = "https://jabermall.com/en/"
//    const val BASE_URL_AR = "https://jabermall.com/ar/"
//    const val BASE_URL= "https://jabermall.com"
const val PHONENUMEBR = "PHONE"
    const val FlagRegestr = "FlagRegestr"

    const val SHARED_PREF = "Montak_KEY"
    const val BASE_URL= "http://montek.com.dedi5536.your-server.de/app/"

//    const val FACEBOOK_OR_GOOGLE_PROVIDER = "1"
//    const val MANUAL_SIGN_UP = "2"
//    const val FACEBOOK = "facebook"
//    const val GOOGLE = "google"
//    const val PHONE_PROVIDER = "phoneRegister"
//    const val CONTACT_US_URL = "front_end/contact_us"
//    const val ABOUT_US_URL = "front_end/aboutUs"

    fun getLang(mContext: Context?): String {
        val sharedPreferences = mContext?.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        return sharedPreferences?.getString("lang", "ar")!!
    }

    fun setLang(mContext: Context?,lang: String?){
        val sharedPreferences = mContext?.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putString("lang",lang)
        editor.apply {  }
    }

    fun getUID(mContext: Context?): String {
        val sharedPreferences = mContext?.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        return sharedPreferences?.getString("uid", "0")!!
    }

    fun isBranchSelected(mContext: Context?): Boolean {
        val sharedPreferences = mContext?.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        return sharedPreferences?.getInt("branch_id", 0) != 0
    }

    fun isGuest(mContext: Context?): Boolean {
        val sharedPreferences = mContext?.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        val isUserIDEmpty = sharedPreferences?.getString("uid", "0") == "0"
        if (isUserIDEmpty)
            openLoginActivity(mContext)
        return isUserIDEmpty
    }

    fun hideKeyBoard(activity: Activity) {
        //Find the currently focused view, so we can grab the correct window token from it.
        var view: View? = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        (activity.getSystemService(Activity.INPUT_METHOD_SERVICE)
                as InputMethodManager).hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun isNetWorkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.activeNetwork
        } else {
            TODO("VERSION.SDK_INT < M")
        }
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        return if (networkCapabilities != null) {
            when {
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            }
        } else false
    }

    //open login activity for guest user
    fun openLoginActivity(mContext: Context?) {
        Toast.makeText(mContext, "Context?.getString(R.string.guest_login)", Toast.LENGTH_LONG)
            .show()
        val splashIntent = Intent(mContext, MainActivity::class.java)
        splashIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        mContext?.startActivity(splashIntent)
    }
    fun setDefaultLanguage(context: Context, lang: String?) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        context.resources.updateConfiguration(
            config,
            context.resources.displayMetrics
        )
    }
    @SuppressLint("HardwareIds")
    fun getAndroidID(mContext: Context?): String =
        Settings.Secure.getString(mContext?.contentResolver, Settings.Secure.ANDROID_ID)
}