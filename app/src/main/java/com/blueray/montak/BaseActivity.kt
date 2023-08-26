package com.blueray.montak

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.blueray.montak.helper.ContextWrapper
import com.example.aljabermall.helpers.HelperUtils.getLang
import java.util.*

abstract class BaseActivity :AppCompatActivity(){

    // to save the context of the resources and save the language if destroyed
    override fun attachBaseContext(newBase: Context?) {
        val lang = getLang(newBase!!)
        val local = Locale(lang)
        val newContext = ContextWrapper.wrap(newBase, local)
        super.attachBaseContext(newContext)
    }

}