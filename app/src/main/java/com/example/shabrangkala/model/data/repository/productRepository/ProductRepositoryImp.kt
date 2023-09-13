package com.example.shabrangkala.model.data.repository.productRepository

import com.example.shabrangkala.model.data.ProductToSaveInCartList
import com.example.shabrangkala.model.data.ProductToSaveInWishList
import com.example.shabrangkala.model.data.category.Category
import com.example.shabrangkala.model.data.product.Product
import com.example.shabrangkala.model.data.tag.Tag
import com.example.shabrangkala.model.data.variation.Variation
import com.example.shabrangkala.model.db.CartProductDao
import com.example.shabrangkala.model.db.ProductDao
import com.example.shabrangkala.model.net.ApiService

class ProductRepositoryImp(
    private val apiService: ApiService,
    private val productDao: ProductDao,
    private val cartProductDao: CartProductDao
) : ProductRepository {
    override suspend fun getAllProducts(): List<Product> {
        return apiService.getAllProducts()
    }

    override suspend fun getAllOnSaleProducts(): List<Product> {
        return apiService.getAllOnSaleProducts()
    }

    override suspend fun getPopularTags(): List<Tag> {
        return apiService.getPopularTags()
    }

    override suspend fun getParentCategories(): List<Category> {
        return apiService.getParentCategories()
    }

    override suspend fun getCertainProduct(id: Int): Product {
        return apiService.getCertainProduct(id)
    }

    override suspend fun getProductsOfCertainCategory(id: Int, page: Int): List<Product> {
        return apiService.getProductsOfCertainCategory(id = id, page = page)
    }

    override suspend fun getCategoryById(id: Int): Category {
        return apiService.getCategoryById(id)
    }

    override suspend fun getProductVariations(productId: Int): List<Variation> {
        return apiService.getProductVariations(productId)
    }

    override suspend fun addProductToWishList(productId: Int) {

        productDao.addProductToWishList(ProductToSaveInWishList(productId))
    }

    override suspend fun removeProductFromWishList(productId: Int) {
        productDao.deleteProductFromWishList(ProductToSaveInWishList(productId))
    }

    override suspend fun getAllWishListProducts(): List<Int> {
        return productDao.getAllWishListProducts()
    }

    override suspend fun addProductToCart(id: Int, price: Int, image: String, count: Int) {
        return cartProductDao.addProductToCartList(ProductToSaveInCartList(id, price, image, count))
    }

    override suspend fun getDataFromCartDB(): List<ProductToSaveInCartList> {
        return cartProductDao.getAllCartListProducts()
    }


}