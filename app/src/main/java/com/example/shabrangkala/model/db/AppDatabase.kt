package com.example.shabrangkala.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shabrangkala.model.data.ProductToSaveInWishList
import com.example.shabrangkala.model.data.product.Product

@Database(entities = [ProductToSaveInWishList::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}