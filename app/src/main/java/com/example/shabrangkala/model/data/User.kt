package com.example.shabrangkala.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shabrangkala.model.data.product.Product


@Entity(tableName = "UserData")
data class User (
    @PrimaryKey
    val userName: String,
    val email: String,
    val phoneNumber: String,
    val password: String,
    val listOfOrders: ArrayList<Product>
    )