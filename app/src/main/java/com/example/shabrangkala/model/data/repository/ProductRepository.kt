package com.example.shabrangkala.model.data.repository

import com.example.shabrangkala.model.data.ProductToSaveInWishList
import com.example.shabrangkala.model.data.category.Category
import com.example.shabrangkala.model.data.product.Product
import com.example.shabrangkala.model.data.tag.Tag
import com.example.shabrangkala.model.data.variation.Variation

interface ProductRepository {

    suspend fun getAllProducts(): List<Product>
    suspend fun getPopularTags(): List<Tag>

    suspend fun getParentCategories() : List<Category>

    suspend fun getCertainProduct(id:Int) : Product

    suspend fun getProductsOfCertainCategory(id: Int, page: Int) : List<Product>

    suspend fun getCategoryById(id: Int) : Category

    suspend fun getProductVariations(productId: Int) : List<Variation>

    suspend fun addProductToWishList(productId: Int)

    suspend fun removeProductFromWishList(productId: Int)

    suspend fun getAllWishListProducts() : List<Int>


}