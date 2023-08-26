package com.example.shabrangkala.model.data.repository

import com.example.shabrangkala.model.data.product.Product

interface ProductRepository {

    suspend fun getAllProducts(): List<Product>


}