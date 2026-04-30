package org.loginapp.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

@Serializable
data class DetailScreen(
    val userId: Int,
)
