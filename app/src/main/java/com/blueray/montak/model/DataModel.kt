package com.blueray.montak.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

sealed class NetworkResults<out R> {
    data class Success<out T>(val data: T) : NetworkResults<T>()
    data class Error(val exception: Exception) : NetworkResults<Nothing>()
}
data class Msg(
    @SerializedName("msg") val status: MessageModel,

    )

data class MessageModel(
    @SerializedName("status") val status: Int,
    @SerializedName("message") val msg: String,
)


data class LoginUserDara(
    @SerializedName("msg") val status: MessageModel,
    @SerializedName("data") val user: User? = null,

    )
data class User(

    @SerializedName("id") val id: Int,
    @SerializedName("role") val user_type: String,
    @SerializedName("mail") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("company_name") val company_name: String,
    @SerializedName("company_owner_name") val company_owner_name: String,
    @SerializedName("detailed_address") val detailed_address: String,
    @SerializedName("sector") val sector: String,
    @SerializedName("other_sector") val other_sector: String,
    @SerializedName("lat") val lat: Int,
    @SerializedName("lon") val lon: Int,


    )


data class SectorsItem(
    @SerializedName("msg") val status: MessageModel,
    @SerializedName("data") val user: ArrayList<MSector>? = null,

    )
data class MSector(

    @SerializedName("tid") val tid: String,
    @SerializedName("name") val name: String,
    )

data class GetCategory(
    @SerializedName("msg") val status: MessageModel,
    @SerializedName("data") val items: ArrayList<CategroyItem>? = null,

    )

data class CategroyItem
    (

    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String,

    )
data class GetProudects(
    @SerializedName("msg") val status: MessageModel,
    @SerializedName("data") val items: ArrayList<ProudectModelItem>? = null,

    )


data class CategoryProductModel(

    @SerializedName("msg") val status: MessageModel,

    @SerializedName("data") val categories_with_items: List<CategoriesItems?>
)

data class FavouriteMessageModel(
    @SerializedName("msg") val status: MessageModel
)
data class CategoriesItems(

    @SerializedName("id") val category_id: Int,
    @SerializedName("name") val category_name_ar: String,
    @SerializedName("image") val category_image: String,
    @SerializedName("category_logo") val category_logo: String,
    @SerializedName("status") val status: Int,
    @SerializedName("products") val items: List<ProudectModelItem>,
    var isSelected:Boolean = false
)
@Parcelize
data class ProudectModelItem(

    @SerializedName("pid") val pid: String,
    @SerializedName("tid") val tid: String,
    @SerializedName("vid") val vid: String,
    @SerializedName("title") val title: String,
    @SerializedName("fav") val fav: Int,
    @SerializedName("body") val name: String,
    @SerializedName("price_per_carton") val price_per_carton: String,
    @SerializedName("price") val price: Double,
    @SerializedName("new_arrival") val new_arrival: String,

    @SerializedName("number_of_items_per_carton") val number_of_items_per_carton: String,
    @SerializedName("buy_type") val buy_type: Int,
    @SerializedName("max_quantity") val max_quantity: String,
    @SerializedName("images") val images: String,


): Parcelable






