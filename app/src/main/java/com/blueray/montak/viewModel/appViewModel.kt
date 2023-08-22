package com.blueray.montak.viewModel

import android.app.Application
import android.os.Message
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blueray.montak.model.CategoryProductModel
import com.blueray.montak.model.FavouriteMessageModel
import com.blueray.montak.model.GetCategory
import com.blueray.montak.model.GetProudects
import com.blueray.montak.model.LoginUserDara
import com.blueray.montak.model.Msg
import com.blueray.montak.model.NetworkResults
import com.blueray.montak.model.SectorsItem
import com.blueray.montak.repo.repo
import com.example.aljabermall.helpers.HelperUtils

import kotlinx.coroutines.launch

class appViewModel(application: Application) : AndroidViewModel(application) {
    private val language = HelperUtils.getLang(application.applicationContext)
//    private val uid = HelperUtils.getUID(application.applicationContext)
    private val uid = "18"

    private val contactUsLivedata = MutableLiveData<NetworkResults<Message>>()
    private val loginLive = MutableLiveData<NetworkResults<LoginUserDara>>()
    private val checkPhoen = MutableLiveData<NetworkResults<Msg>>()
    private val getSectorLive = MutableLiveData<NetworkResults<SectorsItem>>()
    private val getSubSectorLive = MutableLiveData<NetworkResults<SectorsItem>>()
    private val getProudectLive = MutableLiveData<NetworkResults<GetProudects>>()
    private val getCatLive = MutableLiveData<NetworkResults<GetCategory>>()
    private val addToFavouriteLiveData = MutableLiveData<NetworkResults<FavouriteMessageModel>>()

    private val productsCategoriesLiveData = MutableLiveData<NetworkResults<CategoryProductModel>>()
    private val productsSubCategoriesLiveData = MutableLiveData<NetworkResults<CategoryProductModel>>()


    fun retrivelogin(phone:String,otp:String) {
        viewModelScope.launch {
            loginLive.value = repo.userLogin(
phone,otp,language

            )
        }
    }

    fun getLogin() = loginLive



    fun checkPhoen(phone:String) {
        viewModelScope.launch {
            checkPhoen.value = repo.checkPhone(
                phone,language

            )
        }
    }

    fun getCheckPhone() = checkPhoen


    fun retriveSectore() {
        viewModelScope.launch {
            getSectorLive.value = repo.getSectors(
            language

            )
        }
    }

    fun getSectore() = getSectorLive

    fun retriveSubSectore(id:String) {
        viewModelScope.launch {
            getSubSectorLive.value = repo.getSubSectors(
             id,   language

            )
        }
    }

    fun getSubSectore() = getSubSectorLive

    fun retriveProudects(categeoryId:String,searchText:String) {
        viewModelScope.launch {
            getProudectLive.value = repo.getProudects(
                uid, categeoryId,searchText,  language

            )
        }
    }

    fun getProudects() = getProudectLive


    fun retriveProudectFav() {
        viewModelScope.launch {
            getProudectLive.value = repo.getProudectFav(
                uid,language

            )
        }
    }

    fun getProudectsFav() = getProudectLive


    fun retriveCatLive() {
        viewModelScope.launch {
            getCatLive.value = repo.getCategoryItem(

language
            )
        }
    }

    fun getCats() = getCatLive
    fun retrieveProductCategories() {
        viewModelScope.launch {
            productsCategoriesLiveData.value =
                repo.getProductsCategories(language, uid)
        }
    }
    fun getProductsCate() = productsCategoriesLiveData


    fun retrieveProductSubCategories(cid:String) {
        viewModelScope.launch {
            productsSubCategoriesLiveData.value = repo.getProductsSubCategories(language, uid,cid)
        }
    }

    fun getSubCategory()=productsSubCategoriesLiveData

    fun addToFavourite(productId: String) {
        viewModelScope.launch {
            addToFavouriteLiveData.value =
                repo.addToFavourite(language, uid, productId)
        }
    }


    fun getAddToFavouriteMessage() = addToFavouriteLiveData



}