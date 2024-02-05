package com.ricky.jetpet.domain.model

import androidx.annotation.DrawableRes
import com.ricky.jetpet.R

data class Pet(
    var name: String = "",
    var gender: String = "",
    var age: String = "",
    var breed: String = "",
    var color: String = "",
    var location: String = "",
    @DrawableRes var image: Int = R.drawable.blue_dog,
    var description: String = "",
    var owner: Owner = Owner(),
    var id: Int =0
)
