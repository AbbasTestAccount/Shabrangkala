package com.example.shabrangkala.model.net

import com.example.shabrangkala.model.data.product.Product
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


const val BASE_URL = "https://shabrangkala.ir"

interface ApiService {

    @GET("/wp-json/wc/v3/products")
    suspend fun getAllProducts() : List<Product>

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