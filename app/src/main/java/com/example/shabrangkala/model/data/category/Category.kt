package com.example.shabrangkala.model.data.category

data class Category(
    val _links: Links,
    val count: Int,
    val description: String,
    val display: String,
    val id: Int,
    val image: Image,
    val menu_order: Int,
    val name: String,
    val parent: Int,
    val slug: String,
    val yoast_head: String,
    val yoast_head_json: YoastHeadJson
)