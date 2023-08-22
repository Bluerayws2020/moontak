package com.blueray.montak.api


import com.blueray.montak.model.CategoryProductModel
import com.blueray.montak.model.FavouriteMessageModel
import com.blueray.montak.model.GetCategory
import com.blueray.montak.model.GetProudects
import com.blueray.montak.model.LoginUserDara
import com.blueray.montak.model.Msg
import com.blueray.montak.model.SectorsItem
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiServices {


    @Multipart
    @POST("app/login-user")
    suspend fun Login(

        @Part("phone") phone: RequestBody,

        @Part("otp") otp: RequestBody,

        ): LoginUserDara



    @Multipart
    @POST("app/checkPhone")
    suspend fun checkPhone(

        @Part("phone") phone: RequestBody,


        ): Msg


    @POST("app/getSectors")
    suspend fun getSectors(



        ): SectorsItem



    @Multipart
    @POST("app/getSectors")
    suspend fun getSubSectors(

        @Part("parent_tid") parent_tid: RequestBody,


        ): SectorsItem

    @Multipart
    @POST("app/get-products")
    suspend fun getProudctc(

        @Part("uid") uid: RequestBody,
        @Part("catagory") catagory: RequestBody,
        @Part("search_text") search_text: RequestBody,


        ): GetProudects

    @Multipart
    @POST("app/get-main-department")
    suspend fun     getMaindepartment(

        @Part("lang") uid: RequestBody,


        ): GetCategory

    @Multipart
    @POST("app/get-fav-products")
    suspend fun getFav(

        @Part("uid") uid: RequestBody,



        ): GetProudects


    @Multipart
    @POST("app/get-sub-department")
    suspend fun getProductsCategories(

        @Part("uid") userId: RequestBody,


        ): CategoryProductModel


    @Multipart
    @POST("app/get-sub-department")
    suspend fun getProductsSubCategories(


        @Part("lang") language: RequestBody,
        @Part("uid") uid:RequestBody,
        @Part("parent_id") cid: RequestBody,

        ): CategoryProductModel

    @Multipart
    @POST("app/add-favorite")
    suspend fun addToFavourite(

        @Part("uid") userId: RequestBody,
        @Part("product_id") productId: RequestBody,
    ): FavouriteMessageModel
}
