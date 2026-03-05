package com.example.tapflow.navigation

sealed class AppRoute(val route: String) {

    data object Nfc : AppRoute("nfc")

    data object ConfigureTag : AppRoute("configure_tag/{uid}") {

        fun create(uid: String) =
            route.replace("{uid}", uid)
    }
}