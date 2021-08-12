package com.androiddevs.twistcode.repository

import com.androiddevs.twistcode.api.RetrofitInstance
import com.androiddevs.twistcode.db.ProductDatabase
import com.androiddevs.twistcode.model.ProductResponseItem

class ProductsRepository(val db: ProductDatabase) {
    suspend fun getProducts() = RetrofitInstance.api.getProducts()

    //---------------DB---------
    suspend fun upsertcart(product: ProductResponseItem) = db.getProductDao().updateinsert(product)

    fun getcart() = db.getProductDao().getAllProducts()

    suspend fun deletecart(product: ProductResponseItem) = db.getProductDao().deleteProducts(product)
}