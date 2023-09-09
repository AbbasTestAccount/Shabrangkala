package com.example.shabrangkala.model.data.repository.blogRepository

import com.example.shabrangkala.model.data.blog.Blog
import com.example.shabrangkala.model.net.ApiService

class BlogRepositoryImp(
    private val apiService: ApiService
) : BlogRepository {
    override suspend fun getPopularBlogPosts(): List<Blog> {
        return apiService.getLastBlogPosts()
    }

    override suspend fun getBlogPost(id: Int): Blog {
        return apiService.getBlogPost(id)
    }


}