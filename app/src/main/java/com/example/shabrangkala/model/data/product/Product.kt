package com.example.shabrangkala.model.data.product



data class Product(
    val attributes: List<Attribute>,
    val average_rating: String,
    val catalog_visibility: String,
    val categories: List<Category>,
    val date_created: String,
    val date_created_gmt: String,
    val date_modified: String,
    val date_modified_gmt: String,
    val date_on_sale_from: Any,
    val date_on_sale_from_gmt: Any,
    val date_on_sale_to: Any,
    val date_on_sale_to_gmt: Any,
    val default_attributes: List<DefaultAttribute>,
    val description: String,
    val featured: Boolean,
    val grouped_products: List<Any>,
    val id: Int,
    val images: List<Image>,
    val low_stock_amount: Any,
    val menu_order: Int,
    val name: String,
    val on_sale: Boolean,
    val parent_id: Int,
    val permalink: String,
    val price: String,
    val purchasable: Boolean,
    val purchase_note: String,
    val rating_count: Int,
    val regular_price: String,
    val sale_price: String,
    val short_description: String,
    val sku: String,
    val slug: String,
    val status: String,
    val stock_status: String,
    val tags: List<Tag>,
    val total_sales: Int,
    val type: String,
    val upsell_ids: List<Any>,
    val variations: List<Int>,
    val weight: String
){
    fun isProductEmpty(): Boolean {
        return name == ""
    }
}