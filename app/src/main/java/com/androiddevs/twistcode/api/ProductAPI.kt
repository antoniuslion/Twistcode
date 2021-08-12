package com.androiddevs.twistcode.api

import com.androiddevs.twistcode.model.ProductResponse
import retrofit2.Response
import retrofit2.http.POST

interface ProductAPI {

    @POST("index.php/rest/items/search/api_key/teampsisthebest")
    suspend fun getProducts(

    ): Response<ProductResponse>
}