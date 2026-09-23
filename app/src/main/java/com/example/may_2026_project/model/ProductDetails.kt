package com.example.may_2026_project.model

import androidx.annotation.DrawableRes

data class ProductDetails(
    val itemName: String,
    val itemDescription: List<String>,
    val itemCost: String,
    @DrawableRes val itemImageRes: Int
)