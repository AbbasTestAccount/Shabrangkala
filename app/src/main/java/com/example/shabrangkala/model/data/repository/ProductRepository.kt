package com.example.shabrangkala.model.data.repository

import com.example.shabrangkala.model.data.category.Category
import com.example.shabrangkala.model.data.product.Product
import com.example.shabrangkala.model.data.tag.Tag

interface ProductRepository {

    suspend fun getAllProducts(): List<Product>
    suspend fun getPopularTags(): List<Tag>

    suspend fun getParentCategories() : List<Category>

    suspend fun getCertainProduct(id:Int) : Product


}