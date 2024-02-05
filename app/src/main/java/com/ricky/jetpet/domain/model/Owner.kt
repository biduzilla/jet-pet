package com.ricky.jetpet.domain.model

import androidx.annotation.DrawableRes
import com.ricky.jetpet.R

data class Owner(
    val name: String = "",
    val basicInfo: String = "",
    @DrawableRes val image: Int = R.drawable.blue_dog
)
