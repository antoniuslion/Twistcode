package com.androiddevs.twistcode.view.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.androiddevs.twistcode.R
import com.androiddevs.twistcode.adapters.CartsAdapter
import com.androiddevs.twistcode.adapters.ProductsAdapter
import com.androiddevs.twistcode.util.Resource
import com.androiddevs.twistcode.view.HomeActivity
import com.androiddevs.twistcode.view.ProductsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_products.*

class ProductFragment : Fragment(R.layout.fragment_products) {

    companion object {
        var count = 0
    }

    //viewmodel
    lateinit var viewModel: ProductsViewModel
    lateinit var productAdapter: ProductsAdapter

    val TAG = "ProductFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as HomeActivity).viewModel
        btnCart.setText(count.toString())
        CartFragment.Total = 0
        setupRecyclerView()

        productAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("product", it)
            }
            viewModel.addCart(it)
            //notify if saved
            Snackbar.make(view, "Product Added to Cart", Snackbar.LENGTH_SHORT).show()

            count++
            btnCart.setText(count.toString())
        }

        btnCart.setOnClickListener {
            findNavController().navigate(
                R.id.action_productFragment_to_cartFragment
            )
        }

        viewModel.Products.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { productsResponse ->
                        productAdapter.differ.submitList(productsResponse)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG)
                            .show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar(){
        paginationProgressBar.visibility = View.INVISIBLE
        buttoncategory.visibility = View.VISIBLE
        buttonfilter.visibility = View.VISIBLE
    }

    private fun showProgressBar(){
        paginationProgressBar.visibility = View.VISIBLE
        buttoncategory.visibility = View.INVISIBLE
        buttonfilter.visibility = View.INVISIBLE
    }



    private fun setupRecyclerView(){
        productAdapter = ProductsAdapter()
        rvCart.apply {
            adapter = productAdapter
            layoutManager = GridLayoutManager(activity, 2)
        }
    }

}