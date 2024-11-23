package com.n3vers4ydie.unieventos.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import com.n3vers4ydie.unieventos.models.Screen
import com.n3vers4ydie.unieventos.models.UserModel

@Composable
fun AppNavigationBar(
    navController: NavHostController, loggedUserState: MutableState<UserModel?>
) {
    NavigationBar {
        val screens = mutableListOf(
            Screen("Eventos", ScreenRoute.Events, Icons.Filled.Event),
            Screen("Cuenta", ScreenRoute.User, Icons.Filled.Person)
        )

        if (loggedUserState.value?.admin == false) {
            screens.add(screens.size - 1, Screen("Carrito", ScreenRoute.Cart, Icons.Filled.ShoppingCart))
            screens.add(screens.size - 1, Screen("Historial", ScreenRoute.History, Icons.Filled.History))
        }

        val currentRoute = navController.currentDestination

        screens.forEach { screen ->
            NavigationBarItem(icon = { Icon(screen.icon, contentDescription = screen.label) },
                label = { Text(screen.label) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        launchSingleTop = true
                        restoreState = true
                        navController.popBackStack()
                    }
                })
        }
    }
}