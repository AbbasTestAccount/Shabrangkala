package com.example.shabrangkala.model.data.repository

import com.example.shabrangkala.model.data.blog.Blog
import com.example.shabrangkala.model.data.category.Category
import com.example.shabrangkala.model.data.product.Product
import com.example.shabrangkala.model.data.tag.Tag
import com.example.shabrangkala.model.net.ApiService

class BlogRepositoryImp(
    private val apiService: ApiService
) : BlogRepository {
    override suspend fun getPopularBlogPosts(): List<Blog> {
        return apiService.getLastBlogPosts()
    }


}