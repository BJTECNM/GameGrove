package com.gamegrove.viewmodel.data.datagames

import androidx.annotation.DrawableRes

data class Game(
    val title: String,
    @DrawableRes val image: Int,
    val launch: Int,
    val plataform: String,
    val genre: String
)
