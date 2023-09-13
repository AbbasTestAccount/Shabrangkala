package com.example.shabrangkala.model.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shabrangkala.model.data.ProductToSaveInWishList

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProductToWishList(productToSaveInWishList: ProductToSaveInWishList)

    @Delete
    suspend fun deleteProductFromWishList(productToSaveInWishList: ProductToSaveInWishList)

    @Query("SELECT id FROM productInWishList")
    suspend fun getAllWishListProducts(): List<Int>

}