package com.example.shabrangkala.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shabrangkala.model.data.Search
import com.example.shabrangkala.model.data.ProductToSaveInCartList
import com.example.shabrangkala.model.data.ProductToSaveInWishList

@Database(entities = [ProductToSaveInWishList::class, ProductToSaveInCartList::class, Search::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    abstract fun cartProductDao(): CartProductDao

    abstract fun searchDao(): SearchedDao
}