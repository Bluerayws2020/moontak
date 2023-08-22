package com.blueray.montak.interfaces

import com.blueray.montak.model.GetProudects

interface OnProductListener {
    fun addToCart(price: Double?, pid: Int?, quantity: String)
    fun addToFavourite(pid: Int)
    fun removeFromFavourite(favId: Int)
    fun showDetails(product: GetProudects)
}