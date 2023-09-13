package com.example.shabrangkala.ui.featurs.cartScreen

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shabrangkala.model.data.ProductToSaveInCartList
import com.example.shabrangkala.model.data.repository.productRepository.ProductRepository
import com.example.shabrangkala.model.data.variation.Variation
import com.example.shabrangkala.utils.EMPTY_PRODUCT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartScreenViewModel(private val productRepository: ProductRepository) : ViewModel() {

    var cartList = mutableStateOf<List<ProductToSaveInCartList>>(listOf())

    fun addProductToCart(id: Int, price: Int, image: String, count:Int){
        viewModelScope.launch( Dispatchers.IO) {
            productRepository.addProductToCart(id, price, image, count)
        }
    }

    fun getDataFromCartDB(){
        viewModelScope.launch(Dispatchers.IO){
            cartList.value = productRepository.getDataFromCartDB()
        }
    }


    fun removeProductFromDb(productToSaveInCartList: ProductToSaveInCartList){
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.removeDataFromDB(productToSaveInCartList)
        }
    }
}



