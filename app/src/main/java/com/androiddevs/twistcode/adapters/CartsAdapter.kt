package com.androiddevs.twistcode.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.twistcode.R
import com.androiddevs.twistcode.model.ProductResponseItem
import com.androiddevs.twistcode.view.Fragments.CartFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragments_carts.view.*
import kotlinx.android.synthetic.main.item_carts.*
import kotlinx.android.synthetic.main.item_carts.view.*
import kotlinx.android.synthetic.main.item_products.view.*

class CartsAdapter: RecyclerView.Adapter<CartsAdapter.CartViewHolder>(){

    inner class CartViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    private val differCallback = object : DiffUtil.ItemCallback<ProductResponseItem>(){
        override fun areItemsTheSame(
            oldItem: ProductResponseItem,
            newItem: ProductResponseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ProductResponseItem,
            newItem: ProductResponseItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_carts,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.itemView.apply {
            if(product.default_photo?.img_path != ""){
                Glide.with(this).load("https://ranting.twisdev.com/uploads/" + product.default_photo?.img_path).into(imageProduct)
            }
            else
            {
                Glide.with(this).load(R.drawable.defaultfood).into(imageProduct)
            }

            tvNameProduct.text = product.title
            tvPrice.text = "Rp  " + product.price
            tvStatus.text = product.condition_of_item?.name
            tvWeight.text = product.weight + " kg"
            tvQuantity.text = "0"
            var split = tvPrice.text.toString().split("  ")


            btnMinus.setOnClickListener {
                var now = tvQuantity.text.toString()
                var count = 0
                var temp = 0
                var temp2 = 0
                var split = tvPrice.text.toString().split("  ")
                if(now.toInt() > 0 ){
                    temp2 = now.toInt() * split[1].toInt()
                    count = now.toInt()-1
                    temp = count * split[1].toInt()
                    CartFragment.Total = (CartFragment.Total + temp) - temp2
                    tvQuantity.setText(count.toString())
                    onItemClickListener?.let { it(product) }
                }
                else
                {
                    tvQuantity.setText("0")
                    onItemClickListener?.let { it(product) }
                }
            }

            btnPlus.setOnClickListener {
                var now = tvQuantity.text.toString()
                var count = 0
                var temp = 0
                var temp2 = 0
                var split = tvPrice.text.toString().split("  ")
                temp2 = now.toInt() * split[1].toInt()
                count = now.toInt()+1
                temp = count * split[1].toInt()
                CartFragment.Total = (CartFragment.Total + temp) - temp2
                tvQuantity.setText(count.toString())
                onItemClickListener?.let { it(product) }
            }

            setOnClickListener {
                onItemClickListener?.let { it(product) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    //handle onclick item
    private var onItemClickListener: ((ProductResponseItem)-> Unit)? = null

    fun setOnItemClickListener(listener: (ProductResponseItem) -> Unit){
        onItemClickListener = listener
    }


}
