package com.example.shabrangkala.model.data.tag

data class Tag(
    val _links: Links,
    val count: Int,
    val description: String,
    val id: Int,
    val name: String,
    val slug: String,
    val yoast_head: String,
    val yoast_head_json: YoastHeadJson
)