package com.example.shabrangkala.ui.featurs.cartScreen

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.shabrangkala.model.data.repository.productRepository.ProductRepository
import com.example.shabrangkala.model.data.variation.Variation
import com.example.shabrangkala.utils.EMPTY_PRODUCT

class CartScreenViewModel(private val productRepository: ProductRepository) : ViewModel() {
    var productData = mutableStateOf(EMPTY_PRODUCT)
    var productVariations = mutableStateOf<List<Variation>>(listOf())
    var productPrice = mutableIntStateOf(0)

    val list = mutableStateListOf("sad", "sasasa", "saasa", "saassas", "sadq", "sasasra", "sasua", "sasans", "sadv", "sas5asa", "sa7sa", "sas3as")



}