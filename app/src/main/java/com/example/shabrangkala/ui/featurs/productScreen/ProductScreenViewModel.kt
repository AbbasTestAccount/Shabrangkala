package com.example.shabrangkala.ui.featurs.productScreen

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shabrangkala.model.data.product.Product
import com.example.shabrangkala.model.data.repository.productRepository.ProductRepository
import com.example.shabrangkala.model.data.variation.Variation
import com.example.shabrangkala.utils.EMPTY_PRODUCT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductScreenViewModel(private val productRepository: ProductRepository) : ViewModel() {
    var productData = mutableStateOf(EMPTY_PRODUCT)
    var productVariations = mutableStateOf<List<Variation>>(listOf())
    var productPrice = mutableIntStateOf(0)


    fun loadProductData(id: Int) {
        viewModelScope.launch() {

            productData.value = productRepository.getCertainProduct(id)

        }
    }

    fun clearProductData() {
        productData = mutableStateOf(EMPTY_PRODUCT)
        productPrice = mutableIntStateOf(0)
    }

    fun loadProductVariations(id: Int) {
        viewModelScope.launch {
            productVariations.value = productRepository.getProductVariations(id)
        }
    }

    fun addProductToWishList(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.addProductToWishList(product.id)
        }
    }

    fun removeProductFromWishList(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.removeProductFromWishList(product.id)
        }
    }


    fun findPriceWithVariation(selectedVariations: MutableMap<String, String>) {
        for (i in 0 until productVariations.value.size) {
            var flag = true
            for (j in 0 until productVariations.value[i].attributes.size) {
                selectedVariations.forEach { (key, value) ->
                    Log.e("sallkjshkjbb", "i = $i, j = $j \n"+ productVariations.value[i].attributes[j].name + "\n" + productVariations.value[i].attributes[j].option )
                    if (key == productVariations.value[i].attributes[j].name && value != productVariations.value[i].attributes[j].option) {
                        flag = false

                    }
                }
                if (flag) {
                    productPrice.intValue = productVariations.value[i].price.toInt()
                    Log.e("sallkjshkjbb", "oomad in ja")

                    return
                }
            }
        }
    }
}