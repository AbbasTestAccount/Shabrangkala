package com.example.shabrangkala.ui.featurs.mainScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shabrangkala.model.data.blog.Blog
import com.example.shabrangkala.model.data.category.Category
import com.example.shabrangkala.model.data.product.Product
import com.example.shabrangkala.model.data.repository.blogRepository.BlogRepository
import com.example.shabrangkala.model.data.repository.productRepository.ProductRepository
import com.example.shabrangkala.model.data.tag.Tag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val productRepository: ProductRepository,
    private val blogRepository: BlogRepository
) : ViewModel() {
    val listProductImage = mutableStateOf<List<Product>>(listOf())
    val listPopularTags = mutableStateOf<List<Tag>>(listOf())
    val listCategory = mutableStateOf<List<Category>>(listOf())
    val listLastBlogPosts = mutableStateOf<List<Blog>>(listOf())
    var wishListProductsId = mutableStateOf<List<Int>>(listOf())
    var wishListProducts = mutableStateOf<List<Product>>(listOf())


    init {
        viewModelScope.launch(Dispatchers.IO) {
            async { getPopularTags() }
            async { getProductImage() }
            async { getParentCategory() }
            async { getLastBlogPosts() }
        }
    }


    private suspend fun getProductImage() {

        listProductImage.value = productRepository.getAllProducts()

    }

    private suspend fun getPopularTags() {

        listPopularTags.value = productRepository.getPopularTags()

    }


    private suspend fun getParentCategory() {
        listCategory.value = productRepository.getParentCategories()
    }


//--------------------------------------------------------------------------

    private suspend fun getLastBlogPosts() {
        listLastBlogPosts.value = blogRepository.getPopularBlogPosts()

    }

    //--------------------------------------------------------------------------
    fun getAllWishListProductsId() {
        viewModelScope.launch(Dispatchers.IO) {
            wishListProductsId.value = productRepository.getAllWishListProducts()
        }
    }

    fun loadWishListProductData(listId: List<Int>) {
        viewModelScope.launch(Dispatchers.IO) {

            val arrayListWishList = arrayListOf<Product>()
            for (i in 0 until listId.size) {
                arrayListWishList.add(productRepository.getCertainProduct(listId[i]))
            }

            wishListProducts.value = arrayListWishList

        }
    }

}