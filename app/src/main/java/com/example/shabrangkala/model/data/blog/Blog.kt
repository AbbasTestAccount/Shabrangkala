package com.example.shabrangkala.model.data.blog

data class Blog(
    val author: Int,
    val categories: List<Int>,
    val comment_status: String,
    val content: Content,
    val date: String,
    val date_gmt: String,
    val featured_media: Int,
    val format: String,
    val id: Int,
    val link: String,
    val modified: String,
    val modified_gmt: String,
    val slug: String,
    val status: String,
    val sticky: Boolean,
    val tags: List<Any>,
    val template: String,
    val title: Title,
    val type: String,
    val yoast_head_json: YoastHeadJson?
){
    fun isBlogEmpty() : Boolean{
        return title.rendered == ""
    }


}