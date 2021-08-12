package com.androiddevs.twistcode.view

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androiddevs.twistcode.repository.ProductsRepository


class ProductViewModelFactory(val app: Application, val productsRepostory: ProductsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductsViewModel(app,productsRepostory) as T
    }
}