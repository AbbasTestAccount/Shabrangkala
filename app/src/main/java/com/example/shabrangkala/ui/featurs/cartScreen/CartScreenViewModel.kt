package com.example.shabrangkala.ui.featurs.cartScreen

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shabrangkala.model.data.repository.productRepository.ProductRepository
import com.example.shabrangkala.model.data.variation.Variation
import com.example.shabrangkala.utils.EMPTY_PRODUCT
import kotlinx.coroutines.launch

class CartScreenViewModel(private val productRepository: ProductRepository) : ViewModel() {
    var productData = mutableStateOf(EMPTY_PRODUCT)
    var productVariations = mutableStateOf<List<Variation>>(listOf())
    var productPrice = mutableIntStateOf(0)


    fun loadProductData(id: Int) {
        viewModelScope.launch() {

            productData.value = productRepository.getCertainProduct(id)

        }
    }
}