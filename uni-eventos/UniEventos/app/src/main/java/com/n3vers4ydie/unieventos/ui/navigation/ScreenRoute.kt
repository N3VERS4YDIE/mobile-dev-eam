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
    data class Event(val eventId: String) : ScreenRoute()

    @Serializable
    data object Cart : ScreenRoute()

    @Serializable
    data object History : ScreenRoute()

    @Serializable
    data object User: ScreenRoute()

    @Serializable
    data class EditEvent(val eventId: String) : ScreenRoute()

    @Serializable
    data class Message(val text: String) : ScreenRoute()
}