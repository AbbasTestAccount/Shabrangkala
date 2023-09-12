package com.example.shabrangkala.model.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shabrangkala.model.data.ProductToSaveInCartList

@Dao
interface CartProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProductToCartList(productToSaveInCartList: ProductToSaveInCartList)


    @Delete
    suspend fun deleteProductFromWishList(productToSaveInCartList: ProductToSaveInCartList)

    @Query("SELECT * FROM productInCartList")
    suspend fun getAllCartListProducts(): List<ProductToSaveInCartList>
}