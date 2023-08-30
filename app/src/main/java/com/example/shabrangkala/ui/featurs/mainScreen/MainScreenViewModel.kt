package com.example.shabrangkala.ui.featurs.mainScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shabrangkala.model.data.blog.Blog
import com.example.shabrangkala.model.data.category.Category
import com.example.shabrangkala.model.data.product.Product
import com.example.shabrangkala.model.data.repository.BlogRepository
import com.example.shabrangkala.model.data.repository.ProductRepository
import com.example.shabrangkala.model.data.tag.Tag
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val productRepository: ProductRepository,
    private val blogRepository: BlogRepository
) : ViewModel() {
    val listProductImage = mutableStateOf<List<Product>>(listOf())
    val listPopularTags = mutableStateOf<List<Tag>>(listOf())
    val listCategory = mutableStateOf<List<Category>>(listOf())
    val listLastBlogPosts = mutableStateOf<List<Blog>>(listOf())



    init {
        getPopularTags()
        getParentCategory()
        getProductImage()
        getLastBlogPosts()

    }


    private fun getProductImage() {
        viewModelScope.launch() {

            listProductImage.value = productRepository.getAllProducts()

        }

    }

    private fun getPopularTags() {

        viewModelScope.launch() {
            listPopularTags.value = productRepository.getPopularTags()
        }
    }


    private fun getParentCategory() {
        viewModelScope.launch() {
            listCategory.value = productRepository.getParentCategories()
        }
    }


//--------------------------------------------------------------------------

    private fun getLastBlogPosts(){
        viewModelScope.launch {
            listLastBlogPosts.value = blogRepository.getPopularBlogPosts()
        }
    }


}