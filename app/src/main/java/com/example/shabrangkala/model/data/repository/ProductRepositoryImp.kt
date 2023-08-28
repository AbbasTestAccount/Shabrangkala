package com.example.shabrangkala.model.data.repository

import com.example.shabrangkala.model.data.category.Category
import com.example.shabrangkala.model.data.product.Product
import com.example.shabrangkala.model.data.tag.Tag
import com.example.shabrangkala.model.net.ApiService

class ProductRepositoryImp(
    private val apiService: ApiService
) : ProductRepository {
    override suspend fun getAllProducts(): List<Product> {
        return apiService.getAllProducts()
    }

    override suspend fun getPopularTags(): List<Tag> {
        return apiService.getPopularTags()
    }

    override suspend fun getParentCategories(): List<Category> {
        return apiService.getParentCategories()
    }


}