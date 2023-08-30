package com.example.shabrangkala.model.data.blog

data class Graph(
    val id: String,
    val type: String,
    val articleSection: List<String>,
    val author: AuthorX,
    val breadcrumb: Breadcrumb,
    val commentCount: Int,
    val dateModified: String,
    val datePublished: String,
    val description: String,
    val headline: String,
    val image: Image,
    val inLanguage: String,
    val isPartOf: IsPartOf,
    val itemListElement: List<ItemElement>,
    val logo: Logo,
    val mainEntityOfPage: MainEntityOfPage,
    val name: String,
    val potentialAction: List<PotentialAction>,
    val publisher: Publisher,
    val sameAs: List<String>,
    val url: String,
    val wordCount: Int
)