package com.example.shabrangkala.model.data.blog

data class PotentialAction(
    val type: String,
    val name: String,
    val queryinput: String,
    val target: List<String>
)