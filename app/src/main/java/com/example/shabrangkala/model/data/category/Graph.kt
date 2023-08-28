package com.example.shabrangkala.model.data.category

data class Graph(
    val id: String,
    val type: String,
    val breadcrumb: Breadcrumb,
    val description: String,
    val image: ImageX,
    val inLanguage: String,
    val isPartOf: IsPartOf,
    val itemListElement: List<ItemElement>,
    val logo: Logo,
    val name: String,
    val potentialAction: List<PotentialAction>,
    val publisher: Publisher,
    val sameAs: List<String>,
    val url: String
)