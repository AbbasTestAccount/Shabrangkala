package com.example.shabrangkala.ui.featurs.categoryListScreen

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shabrangkala.model.data.product.Product
import com.example.shabrangkala.model.data.repository.productRepository.ProductRepository
import kotlinx.coroutines.launch

class CategoryScreenViewModel(private val productRepository: ProductRepository) : ViewModel() {

    var productsData = mutableStateOf<List<Product>>(listOf())
    var categoryName = mutableStateOf("")
    var categoryCount = mutableIntStateOf(0)
    var pageNumber = mutableIntStateOf(1)


    fun loadProductDataOfCategory(id: Int) {
        viewModelScope.launch {
            productsData.value = productRepository.getProductsOfCertainCategory(id, pageNumber.intValue)
            Log.e("abbas22", pageNumber.intValue.toString() )
        }
    }

    fun clearProductsData() {
        productsData = mutableStateOf(listOf())
        categoryName = mutableStateOf("")
        categoryCount = mutableIntStateOf(0)
        pageNumber = mutableIntStateOf(1)
    }

    fun getCategoryNameById(id: Int) {
        viewModelScope.launch {
            categoryName.value = productRepository.getCategoryById(id).name

        }
    }

    fun getProductCount(id: Int) {
        viewModelScope.launch {
            categoryCount.intValue = productRepository.getCategoryById(id).count
        }
    }


}