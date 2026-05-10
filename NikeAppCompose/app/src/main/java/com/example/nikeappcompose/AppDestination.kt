package com.example.nikeappcompose

import kotlinx.serialization.Serializable

sealed interface AppDestination {
    @Serializable
    data object Home : AppDestination

    @Serializable
    data object Shop : AppDestination

    @Serializable
    data object Wishlist : AppDestination

    @Serializable
    data object Cart : AppDestination

    @Serializable
    data object Profile : AppDestination
}