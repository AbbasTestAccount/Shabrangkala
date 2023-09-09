package com.example.shabrangkala.ui.featurs.blogScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shabrangkala.model.data.repository.blogRepository.BlogRepository
import com.example.shabrangkala.utils.EMPTY_BLOG
import kotlinx.coroutines.launch

class BlogScreenViewModel(private val blogRepository: BlogRepository) : ViewModel() {

    var blogData = mutableStateOf(EMPTY_BLOG)
    fun loadBlogData(id: Int) {
        viewModelScope.launch {
            blogData.value = blogRepository.getBlogPost(id)
        }
    }

    fun clearProductData() {
        viewModelScope.launch{
            blogData = mutableStateOf(EMPTY_BLOG)
        }
    }

}