package com.example.shabrangkala.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("productInWishList")
data class ProductToSaveInWishList(
    @PrimaryKey
    val Id : Int
)