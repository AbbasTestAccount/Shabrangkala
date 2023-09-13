package com.example.shabrangkala.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("productInCartList")
data class ProductToSaveInCartList(
    @PrimaryKey
    val Id : Int,
    val price: Int,
    val Image: String,
    val count: Int
)