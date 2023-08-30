package com.example.shabrangkala.ui.featurs.productScreen

import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shabrangkala.model.data.repository.ProductRepository
import com.example.shabrangkala.utils.EMPTY_PRODUCT
import kotlinx.coroutines.launch

class ProductScreenViewModel(private val productRepository: ProductRepository) : ViewModel() {
    var productData = mutableStateOf(EMPTY_PRODUCT)



    fun loadProductData(id: Int) {
        viewModelScope.launch() {

            productData.value = productRepository.getCertainProduct(id)

        }
    }

    fun clearProductData(){
        productData = mutableStateOf(EMPTY_PRODUCT)
    }


}