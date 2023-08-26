package com.example.shabrangkala.ui.featurs.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shabrangkala.model.data.repository.ProductRepository
import kotlinx.coroutines.launch

class MainScreenViewModel(private val productRepository: ProductRepository) : ViewModel() {

    fun getProductImage(index: Int) : String {
        var productImage = "not found"
        viewModelScope.launch {

            productImage = productRepository.getAllProducts()[index].images[0].src

        }
        return productImage
    }


}