package com.example.shabrangkala.utils

import com.example.shabrangkala.model.data.blog.Blog
import com.example.shabrangkala.model.data.blog.Content
import com.example.shabrangkala.model.data.blog.Title
import com.example.shabrangkala.model.data.product.Product

const val SIGN_UP = "signUp"
const val LOG_IN = "logIn"
const val SIGN_UP_SIGN_IN = "SignUpSignIn"
const val ON_BOARDING = "on boarding"
const val MAIN_SCREEN = "mainScreen"
const val START = "start"
const val PRODUCT_SCREEN = "productScreen"
const val BLOG_SCREEN = "BlogScreen"
const val CATEGORY_LIST_SCREEN = "CategoryListScreen"
const val CART_SCREEN = "Cart Screen"
const val PROFILE_SCREEN = "Profile Screen"


val EMPTY_PRODUCT = Product(
    listOf(), "", "", listOf(), "", "", "", "", "", "", "", "", listOf(),
    "", false, listOf(), 0, listOf(), "", 0, "", false, 0, "", "", true, "", 0,
    "", "", "", "", "", "", "", listOf(), 0, "", listOf(), listOf(), ""
)

val EMPTY_BLOG = Blog(
    0,
    listOf(),
    "",
    Content(true, ""),
    "",
    "",
    0,
    "",
    0,
    "",
    "",
    "",
    "",
    "",
    true,
    listOf(),
    "",
    Title(""),
    "",
    null
)


const val TEXT_FIELD_ICON_SIZE = 25