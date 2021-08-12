package com.androiddevs.twistcode.api

import com.androiddevs.twistcode.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {
    companion object{
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY) //get body response
            val client = OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl(BASE_URL) //call base URL on util -> constant
                .addConverterFactory(GsonConverterFactory.create()) //json converter
                .client(client)
                .build()
        }

        val api by lazy {
            retrofit.create(ProductAPI::class.java) //interface function get method
        }
    }
}