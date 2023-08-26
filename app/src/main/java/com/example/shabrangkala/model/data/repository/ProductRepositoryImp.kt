package com.example.shabrangkala.model.data.repository

import com.example.shabrangkala.model.data.product.Product
import com.example.shabrangkala.model.net.ApiService

class ProductRepositoryImp(
    val apiService: ApiService
) : ProductRepository {
    override suspend fun getAllProducts(): List<Product> {

        return apiService.getAllProducts()
    }
}