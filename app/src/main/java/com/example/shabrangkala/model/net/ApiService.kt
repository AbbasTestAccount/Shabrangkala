package com.example.shabrangkala.model.net

import com.example.shabrangkala.model.data.blog.Blog
import com.example.shabrangkala.model.data.category.Category
import com.example.shabrangkala.model.data.product.Product
import com.example.shabrangkala.model.data.tag.Tag
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


const val BASE_URL = "https://shabrangkala.ir"

interface ApiService {

    @GET("/wp-json/wc/v3/products")
    suspend fun getAllProducts() : List<Product>

    @GET("/wp-json/wc/v3/products/tags?orderby=count&order=desc&per_page=20")
    suspend fun getPopularTags(): List<Tag>

    @GET("/wp-json/wc/v3/products/categories?per_page=10&parent=0")
    suspend fun getParentCategories() : List<Category>

    @GET("/wp-json/wc/v3/products/{id}")
    suspend fun getCertainProduct(@Path("id") productId: Int) : Product

    @GET("https://shabrangkala.ir/wp-json/wp/v2/posts")
    suspend fun getLastBlogPosts() : List<Blog>

    @GET("https://shabrangkala.ir/wp-json/wp/v2/posts/{id}")
    suspend fun getBlogPost(@Path("id") blogId: Int) : Blog

    @GET("https://shabrangkala.ir/wp-json/wc/v3/products?per_page=20&categoey={id}")
    suspend fun getProductsOfCertainCategory(@Path("id") categoryId : Int) : List<Product>
}

fun createApiService(): ApiService {

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor {

            val oldRequest = it.request()

            val newRequest = oldRequest.newBuilder()
            newRequest.addHeader("Authorization", "Basic Y2tfNTU1MzI0NmQwZjAyYTM5YmQ2NTMzNzZkOGJlOWFmYzA2OWMxNzk5NTpjc19mOWIxNGZmMmE5YjQ5MGM0MDJmOTM2MjZkNGYxNTE4ZTc3OTM3ZmU4")
            newRequest.addHeader("Accept", "application/json")

            newRequest.method(oldRequest.method, oldRequest.body)

            return@addInterceptor it.proceed(newRequest.build())
        }.build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    return retrofit.create(ApiService::class.java)
}