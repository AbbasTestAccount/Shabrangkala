package com.example.shabrangkala.model.data.repository.userRepository

import com.example.shabrangkala.model.data.ProductToSaveInWishList
import com.example.shabrangkala.model.data.category.Category
import com.example.shabrangkala.model.data.product.Product
import com.example.shabrangkala.model.data.tag.Tag
import com.example.shabrangkala.model.data.variation.Variation
import com.example.shabrangkala.model.db.ProductDao
import com.example.shabrangkala.model.net.ApiService

class UserRepositoryImp(
    private val apiService: ApiService,
    private val productDao: ProductDao
) : UserRepository {

}