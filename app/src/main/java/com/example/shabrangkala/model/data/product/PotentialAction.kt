package com.example.shabrangkala.model.data.product

data class PotentialAction(
    val type: String,
    val queryInput: String,
    val target: List<String>
)