package com.androiddevs.twistcode.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.twistcode.R
import com.androiddevs.twistcode.model.ProductResponseItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_products.view.*

class ProductsAdapter: RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_products,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.itemView.apply {
            if(product.default_photo?.img_path != ""){
                Glide.with(this).load("https://ranting.twisdev.com/uploads/" + product.default_photo?.img_path).into(imageProductList)
            }
            else
            {
                Glide.with(this).load(R.drawable.defaultfood).into(imageProductList)
            }
            if(product.is_halal == "1"){
                Glide.with(this).load(R.drawable.halal).into(imageLogoStatus);
            }
            else{
                Glide.with(this).load(R.drawable.white).into(imageLogoStatus);
            }
            tvNameProductList.text = product.title
            tvPriceProductList.text = "Rp" + product.price
            tvPlace.text = product.location_name
            tvStore.text = product.added_user_name
            if(product.stock == "0"){
                btnStatusProduct.setText("Out Of Stock")
                btnStatusProduct.setTextColor(Color.WHITE)
                btnStatusProduct.setBackgroundColor(Color.GRAY)
            }
            else{
                btnStatusProduct.setText("Ready Stock")
                btnStatusProduct.setTextColor(Color.WHITE)
                btnStatusProduct.setBackgroundColor(Color.parseColor("#1e90ff"))
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