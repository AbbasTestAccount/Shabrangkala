package com.example.shabrangkala.model.data.repository.blogRepository

import com.example.shabrangkala.model.data.blog.Blog
import com.example.shabrangkala.model.data.category.Category
import com.example.shabrangkala.model.data.product.Product
import com.example.shabrangkala.model.data.tag.Tag

interface BlogRepository {

    suspend fun getPopularBlogPosts(): List<Blog>

    suspend fun getBlogPost(id : Int): Blog

}