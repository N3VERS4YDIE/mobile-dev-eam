package com.n3vers4ydie.unieventos.ui.navigation

import kotlinx.serialization.Serializable

sealed class ScreenRoute {

    @Serializable
    data object Login : ScreenRoute()

    @Serializable
    data object Register : ScreenRoute()

    @Serializable
    data object Events : ScreenRoute()

    @Serializable
    data class Event(val eventId: Int) : ScreenRoute()

    @Serializable
    data object Cart : ScreenRoute()

    @Serializable
    data object History : ScreenRoute()

    @Serializable
    data class AddLocality(val eventId: Int) : ScreenRoute()

    @Serializable
    data class ManageEvent(val eventId: Int) : ScreenRoute()
}