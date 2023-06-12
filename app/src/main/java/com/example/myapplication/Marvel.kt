package com.example.myapplication

import androidx.annotation.DrawableRes

data class Marvel(
    val id: Long,
    val name: String,
    @DrawableRes
    var image: Int?,
    val description: String
)