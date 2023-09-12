package com.example.shabrangkala.model.net

import com.example.shabrangkala.model.data.blog.Blog
import com.example.shabrangkala.model.data.category.Category
import com.example.shabrangkala.model.data.product.Product
import com.example.shabrangkala.model.data.tag.Tag
import com.example.shabrangkala.model.data.variation.Variation
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


const val BASE_URL = "https://shabrangkala.ir"

interface ApiService {

    @GET("/wp-json/wc/v3/products")
    suspend fun getAllProducts() : List<Product>

    @GET("/wp-json/wc/v3/products?on_sale=true")
    suspend fun getAllOnSaleProducts() : List<Product>

    @GET("/wp-json/wc/v3/products/tags?orderby=count&order=desc&per_page=20")
    suspend fun getPopularTags(): List<Tag>

    @GET("/wp-json/wc/v3/products/categories?per_page=10&parent=0")
    suspend fun getParentCategories() : List<Category>

    @GET("/wp-json/wc/v3/products/{id}")
    suspend fun getCertainProduct(@Path("id") productId: Int) : Product

    @GET("/wp-json/wp/v2/posts")
    suspend fun getLastBlogPosts() : List<Blog>

    @GET("/wp-json/wp/v2/posts/{id}")
    suspend fun getBlogPost(@Path("id") blogId: Int) : Blog


    @GET("/wp-json/wc/v3/products")
    suspend fun getProductsOfCertainCategory(
        @Query("per_page") pageNum: Int = 20,
        @Query("category") id: Int,
        @Query("page") page: Int
    ): List<Product>

    @GET("/wp-json/wc/v3/products/{productId}/variations?per_page=50")
    suspend fun getProductVariations(@Path("productId") productId: Int): List<Variation>


    @GET("wp-json/wc/v3/products/categories/{id}")
    suspend fun getCategoryById(@Path("id") categoryId: Int): Category

    //todo

//    @POST("customers")
//    suspend fun createCustomer(@Body customer: Customer?): Customer?
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