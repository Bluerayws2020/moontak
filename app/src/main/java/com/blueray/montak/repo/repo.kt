package com.blueray.montak.repo

import android.util.Log
import com.blueray.montak.api.ApiClient
import com.blueray.montak.model.CategoryProductModel
import com.blueray.montak.model.FavouriteMessageModel
import com.blueray.montak.model.GetCategory
import com.blueray.montak.model.GetProudects
import com.blueray.montak.model.LoginUserDara
import com.blueray.montak.model.Msg
import com.blueray.montak.model.NetworkResults
import com.blueray.montak.model.SectorsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

object repo {

    suspend fun userLogin(
        phone: String,
        otp: String,
        lang: String,

        ): NetworkResults<LoginUserDara> {
        return withContext(Dispatchers.IO) {
            val languageBody = lang.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val phoneBody = phone.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val otpBody = otp.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            try {
                val results = ApiClient.retrofitService.Login(
                    phoneBody, otpBody
                )
                NetworkResults.Success(results)
            } catch (e: Exception) {
                NetworkResults.Error(e)
            }
        }
    }

    suspend fun checkPhone(
        phone: String,
        lang: String,

        ): NetworkResults<Msg> {
        return withContext(Dispatchers.IO) {
            val languageBody = lang.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val phoneBody = phone.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            try {
                val results = ApiClient.retrofitService.checkPhone(
                    phoneBody
                )
                NetworkResults.Success(results)
            } catch (e: Exception) {
                NetworkResults.Error(e)
            }
        }
    }


    suspend fun getSectors(
        lang: String,

        ): NetworkResults<SectorsItem> {
        return withContext(Dispatchers.IO) {
            val languageBody = lang.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            try {
                val results = ApiClient.retrofitService.getSectors(
                )
                NetworkResults.Success(results)
            } catch (e: Exception) {
                NetworkResults.Error(e)
            }
        }
    }


    suspend fun getSubSectors(
        lang: String,
        id: String = "5"

    ): NetworkResults<SectorsItem> {
        return withContext(Dispatchers.IO) {
            val languageBody = lang.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val idBody = id.toRequestBody("multipart/form-data".toMediaTypeOrNull())

            try {
                val results = ApiClient.retrofitService.getSubSectors(
                    idBody
                )
                NetworkResults.Success(results)
            } catch (e: Exception) {
                Log.d("sdaldkfmnasnflads", e.localizedMessage)

                NetworkResults.Error(e)

            }
        }
    }


    suspend fun getProudects(
        uid: String = "18",
        catagory: String = "5",
        search_text: String,
        lang: String

    ): NetworkResults<GetProudects> {
        return withContext(Dispatchers.IO) {
            val uidBody = uid.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val search_textBody = search_text.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val  categoryBody= catagory.toRequestBody("multipart/form-data".toMediaTypeOrNull())

            try {
                val results = ApiClient.retrofitService.getProudctc(
                    uidBody, categoryBody,search_textBody
                )
                NetworkResults.Success(results)
            } catch (e: Exception) {
                Log.d("sdaldkfmnasnflads", e.localizedMessage)

                NetworkResults.Error(e)

            }
        }

    }

    suspend fun getProudectFav(
        uid: String = "18",

        lang: String

    ): NetworkResults<GetProudects> {
        return withContext(Dispatchers.IO) {
            val uidBody = uid.toRequestBody("multipart/form-data".toMediaTypeOrNull())

            try {
                val results = ApiClient.retrofitService.getFav(
                    uidBody
                )
                NetworkResults.Success(results)
            } catch (e: Exception) {
                Log.d("sdaldkfmnasnflads", e.localizedMessage)

                NetworkResults.Error(e)

            }
        }

    }

    suspend fun getProductsCategories(
        language: String,
        userId: String,
    ): NetworkResults<CategoryProductModel> {
        return withContext(Dispatchers.IO) {
            val languageBody = language.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val userIdBody = userId.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            try {
                val results =

                    ApiClient.retrofitService.getProductsCategories(

                        userIdBody,

                        )
                NetworkResults.Success(results)
            } catch (e: Exception) {
                NetworkResults.Error(e)
            }
        }
    }

    suspend fun getCategoryItem(

        lang: String = "ar"

    ): NetworkResults<GetCategory> {
        return withContext(Dispatchers.IO) {

            val langBody = lang.toRequestBody("multipart/form-data".toMediaTypeOrNull())

            try {
                val results = ApiClient.retrofitService.getMaindepartment(
                    langBody
                )
                NetworkResults.Success(results)
            } catch (e: Exception) {
                Log.d("sdaldkfmnasnflads", e.localizedMessage)

                NetworkResults.Error(e)

            }
        }

    }


    suspend fun getProductsSubCategories(
        language: String,
        userId: String,
        cid: String
    ): NetworkResults<CategoryProductModel> {
        return withContext(Dispatchers.IO) {
            val languageBody = language.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val userIdBody = userId.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val cid_body = cid.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            try {
                val results =

                    ApiClient.retrofitService.getProductsSubCategories(

                        languageBody,
                        userIdBody,
                        cid_body
                    )
                NetworkResults.Success(results)
            } catch (e: Exception) {
                NetworkResults.Error(e)
            }
        }
    }


    suspend fun addToFavourite(
        language: String,
        userId: String,
        productId: String,
    ): NetworkResults<FavouriteMessageModel> {
        return withContext(Dispatchers.IO) {
            val languageBody = language.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val userIdBody = userId.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val productIdBody = productId.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            try {
                val results = ApiClient.retrofitService.addToFavourite(

                    userIdBody,
                    productIdBody
                )
                NetworkResults.Success(results)
            } catch (e: Exception) {
                NetworkResults.Error(e)
            }
        }
    }






}