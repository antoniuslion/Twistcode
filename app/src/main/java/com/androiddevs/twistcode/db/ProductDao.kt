package com.androiddevs.twistcode.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.androiddevs.twistcode.model.ProductResponseItem

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateinsert(productresponseitem: ProductResponseItem): Long

    //articles tables from Article class
    @Query("SELECT * FROM carts")
    fun getAllProducts(): LiveData<List<ProductResponseItem>>

    @Delete
    suspend fun deleteProducts(productresponseitem: ProductResponseItem)
}