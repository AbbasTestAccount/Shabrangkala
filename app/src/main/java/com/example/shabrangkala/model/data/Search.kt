package com.example.shabrangkala.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Search")
data class Search(
    @PrimaryKey
    val name: String,
    val countFoundedItems: Int
)