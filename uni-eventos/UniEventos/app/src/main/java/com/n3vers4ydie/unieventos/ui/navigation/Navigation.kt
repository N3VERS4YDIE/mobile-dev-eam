package com.n3vers4ydie.unieventos.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.n3vers4ydie.unieventos.controllers.cartItemController
import com.n3vers4ydie.unieventos.controllers.eventController
import com.n3vers4ydie.unieventos.controllers.loggedUser
import com.n3vers4ydie.unieventos.controllers.purchaseOrderController
import com.n3vers4ydie.unieventos.controllers.userController
import com.n3vers4ydie.unieventos.models.CartItemModel
import com.n3vers4ydie.unieventos.models.EventModel
import com.n3vers4ydie.unieventos.ui.screens.AddLocalityScreen
import com.n3vers4ydie.unieventos.ui.screens.CartScreen
import com.n3vers4ydie.unieventos.ui.screens.EventScreen
import com.n3vers4ydie.unieventos.ui.screens.EventsScreen
import com.n3vers4ydie.unieventos.ui.screens.HistoryScreen
import com.n3vers4ydie.unieventos.ui.screens.LoginScreen
import com.n3vers4ydie.unieventos.ui.screens.ManageEventScreen
import com.n3vers4ydie.unieventos.ui.screens.RegisterScreen
import java.util.Date

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenRoute.Login) {
        composable<ScreenRoute.Login> {
            LoginScreen(onLoginClick = { email, password ->
                for (user in userController.getAll()) {
                    if (user.email == email && user.password == password) {
                        loggedUser = user
                        navController.navigate(ScreenRoute.Events)
                    }
                }
            }, onForgotPasswordClick = { }, onRegisterClick = { navController.navigate(ScreenRoute.Register) })
        }

//        composable("home") {
//            HomeScreen()
//        }

//        composable("forgotPassword") {
//            ForgotPasswordScreen(
//                onBackClick = { navController.popBackStack() }
//            )
//        }

        composable<ScreenRoute.Register> {
            RegisterScreen(onRegisterClick = {
                userController.save(it)
                navController.navigate(ScreenRoute.Events)
            }, onLoginClick = { navController.popBackStack() })
        }

        composable<ScreenRoute.Events> {
            EventsScreen(onEventClick = { navController.navigate(ScreenRoute.Event(it)) },
                onAddEventClick = { navController.navigate(ScreenRoute.ManageEvent) })
        }

        composable<ScreenRoute.Event> {
            val eventRoute = it.toRoute<ScreenRoute.Event>()
            val event = eventController.getById(eventRoute.eventId)!!

            EventScreen(eventId = eventRoute.eventId, onAddToCartClick = { locality, quantity ->
                val cartItem = CartItemModel(event = event, locality = locality, quantity = quantity)
                cartItemController.save(cartItem)
                navController.navigate(ScreenRoute.Cart)
            }, onEditEventClick = { navController.navigate(ScreenRoute.ManageEvent(event.id)) }, onDeleteEventClick = {
                eventController.delete(event.id)
                navController.popBackStack()
            })
        }

        composable<ScreenRoute.Cart> {
            val event = it.toRoute<ScreenRoute.Cart>()

            CartScreen(userId = 0, onCheckout = { purchaseOrder ->
                purchaseOrderController.save(purchaseOrder)
                navController.navigate(ScreenRoute.History)
            })
        }

        composable<ScreenRoute.History> {
            HistoryScreen(userId = 0)
        }

        composable<ScreenRoute.ManageEvent> {
            val manageEventRoute = it.toRoute<ScreenRoute.ManageEvent>()
            val event = eventController.getById(manageEventRoute.eventId)

            ManageEventScreen(event = event ?: EventModel(
                name = "",
                date = Date(),
                location = "",
                description = "",
                posterUrl = "",
                localities = mutableListOf()
            ), onSaveEvent = { updatedEvent ->
                eventController.save(updatedEvent)
                navController.popBackStack()
            }, onAddLocalityClick = { currentEvent ->
                navController.navigate(ScreenRoute.AddLocality(currentEvent.id))
            })
        }

        composable<ScreenRoute.AddLocality> {
            val eventRoute = it.toRoute<ScreenRoute.AddLocality>()
            val event = eventController.getById(eventRoute.eventId)!!

            AddLocalityScreen(event = event, onSaveLocality = { locality ->
                event.localities.add(locality)
                navController.popBackStack()
            })
        }
    }
}