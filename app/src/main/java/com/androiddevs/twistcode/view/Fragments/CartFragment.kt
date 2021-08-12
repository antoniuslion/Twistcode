package com.androiddevs.twistcode.view.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.twistcode.R
import com.androiddevs.twistcode.adapters.CartsAdapter
import com.androiddevs.twistcode.adapters.ProductsAdapter
import com.androiddevs.twistcode.view.HomeActivity
import com.androiddevs.twistcode.view.ProductsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_products.*
import kotlinx.android.synthetic.main.fragment_products.rvCart
import kotlinx.android.synthetic.main.fragments_carts.*
import kotlinx.android.synthetic.main.item_carts.*
import kotlinx.android.synthetic.main.item_carts.view.*
import java.lang.Integer.parseInt

class CartFragment : Fragment(R.layout.fragments_carts) {

    companion object {
        var Total = 0
    }

    //viewmodel
    lateinit var viewModel: ProductsViewModel
    lateinit var cartAdapter: CartsAdapter



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as HomeActivity).viewModel
        setupRecyclerView()

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val product = cartAdapter.differ.currentList[position]
                viewModel.deleteCart(product)
                Snackbar.make(view, "Successfully Deleted Product from Cart", Snackbar.LENGTH_LONG).apply {
                    //undo action
                    setAction("Undo"){
                        viewModel.addCart(product)
                        ProductFragment.count ++
                    }
                    show()
                    ProductFragment.count --
                    if(ProductFragment.count<0){
                        ProductFragment.count=0
                    }
                }
            }

        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(rvCartProduct)
        }


        viewModel.getCart().observe(viewLifecycleOwner, Observer {products ->
            cartAdapter.differ.submitList(products)
        })

        cartAdapter.setOnItemClickListener {
            tvTotal.setText("Rp " + Total.toString())
        }

    }


    private fun setupRecyclerView(){
        cartAdapter = CartsAdapter()
        rvCartProduct.apply {
            adapter = cartAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}