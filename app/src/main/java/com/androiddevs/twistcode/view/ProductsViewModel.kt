package com.androiddevs.twistcode.view

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.twistcode.ProductApplication
import com.androiddevs.twistcode.model.ProductResponse
import com.androiddevs.twistcode.model.ProductResponseItem
import com.androiddevs.twistcode.repository.ProductsRepository
import com.androiddevs.twistcode.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class ProductsViewModel(app: Application, val productsRepository: ProductsRepository) : AndroidViewModel(app){
    val Products: MutableLiveData<Resource<ProductResponse>> = MutableLiveData()

    init {
        getProducts()
    }

    fun getProducts() = viewModelScope.launch{
        //Products.postValue(Resource.Loading())
        //val response = productsRepository.getProducts()
        //Log.e("xx",response.body().toString())
        //Log.e("xx", response.body()?.size.toString())
        //Log.e("xx",response.toString())
        //Products.postValue(handleProductResponse(response))
        safeProductCall()
    }

    private fun handleProductResponse(response: Response<ProductResponse>) : Resource<ProductResponse>
    {
        if(response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private  suspend fun  safeProductCall(){
        Products.postValue(Resource.Loading())
        try {
            if(hasInternetConnection()){
                val response = productsRepository.getProducts()
                Products.postValue(handleProductResponse(response))
            }else{
                Products.postValue(Resource.Error("No Internet Connection"))
            }
        }catch (t: Throwable){
            when(t){
                is IOException -> Products.postValue(Resource.Error("Network Failure"))
                else -> Products.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    //--------DB ViewModel--------------

    fun addCart(product: ProductResponseItem) = viewModelScope.launch {
        productsRepository.upsertcart(product)
    }

    //bukan suspend function jadi tidak pakai viewmodelscope (coroutine)
    fun getCart() = productsRepository.getcart()

    fun deleteCart(product: ProductResponseItem) = viewModelScope.launch {
        productsRepository.deletecart(product)
    }

    //check internet connection
    private  fun  hasInternetConnection(): Boolean{
        val connectivityManager = getApplication<ProductApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        //Build.VERSION_CODES.M = API 23
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }else {
            connectivityManager.activeNetworkInfo?.run {
                return when(type){
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
}