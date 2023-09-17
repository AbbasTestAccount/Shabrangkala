package com.example.shabrangkala.model.data.repository.searchRepository

import com.example.shabrangkala.model.data.Search
import com.example.shabrangkala.model.data.ProductToSaveInCartList
import com.example.shabrangkala.model.data.category.Category
import com.example.shabrangkala.model.data.product.Product
import com.example.shabrangkala.model.data.tag.Tag
import com.example.shabrangkala.model.data.variation.Variation

interface SearchRepository {

    suspend fun getAllSearches(): List<Search>

    suspend fun addSearchToList(search: Search)

    suspend fun deleteSearchItem(search: Search)

}