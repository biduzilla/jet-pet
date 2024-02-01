package com.ricky.jetpet.data.model

import androidx.annotation.DrawableRes

data class Owner(
    val name: String,
    val basicInfo: String,
    @DrawableRes val image: Int
)
