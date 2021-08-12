package com.androiddevs.twistcode.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.androiddevs.twistcode.R
import com.androiddevs.twistcode.db.ProductDatabase
import com.androiddevs.twistcode.repository.ProductsRepository
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

    lateinit var  viewModel: ProductsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val productsRepository = ProductsRepository(ProductDatabase(this))
        val viewModelProviderFactory = ProductViewModelFactory(application, productsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(ProductsViewModel::class.java)

        //bottomNavigationView.setupWithNavController(productsNavHostFragment.findNavController())
    }
}
