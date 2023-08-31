package com.example.shabrangkala.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.shabrangkala.R

// Set of Material typography styles to start with
val Open_Sans = FontFamily(
    Font(R.font.open_sans)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Open_Sans,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Open_Sans,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp

    ),
    titleSmall = TextStyle(
        fontFamily = Open_Sans,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp

    ),
    displaySmall = TextStyle(
        fontFamily = Open_Sans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Open_Sans,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Open_Sans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
        color = LiteBlue
    )


    /* Other default text styles to override

    labelSmall = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    fontSize = 11.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp
    )
    */
)