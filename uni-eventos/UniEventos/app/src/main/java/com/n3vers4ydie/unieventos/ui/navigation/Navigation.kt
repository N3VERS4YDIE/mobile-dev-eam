package com.n3vers4ydie.unieventos.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.n3vers4ydie.unieventos.controllers.UserController
import com.n3vers4ydie.unieventos.controllers.cartItemController
import com.n3vers4ydie.unieventos.controllers.eventController
import com.n3vers4ydie.unieventos.controllers.purchaseOrderController
import com.n3vers4ydie.unieventos.controllers.userController
import com.n3vers4ydie.unieventos.models.CartItemModel
import com.n3vers4ydie.unieventos.models.EventModel
import com.n3vers4ydie.unieventos.models.UserModel
import com.n3vers4ydie.unieventos.ui.screens.CartScreen
import com.n3vers4ydie.unieventos.ui.screens.EditEventScreen
import com.n3vers4ydie.unieventos.ui.screens.EventScreen
import com.n3vers4ydie.unieventos.ui.screens.EventsScreen
import com.n3vers4ydie.unieventos.ui.screens.HistoryScreen
import com.n3vers4ydie.unieventos.ui.screens.LoginScreen
import com.n3vers4ydie.unieventos.ui.screens.MessageScreen
import com.n3vers4ydie.unieventos.ui.screens.UserScreen

@Composable
fun Navigation(navController: NavHostController, loggedUserState: MutableState<UserModel?>) {
    NavHost(navController = navController, startDestination = ScreenRoute.Login) {
        composable<ScreenRoute.Login> {
            LoginScreen(onLoginClick = { users, email, password ->
                for (user in users) {
                    if (user.email == email && user.password == password) {
                        UserController.loggedUser = user
                        navController.navigate(ScreenRoute.Events) {
                            loggedUserState.value = user
                            popUpTo(ScreenRoute.Login) { inclusive = true }
                        }
                    }
                }
            }, onForgotPasswordClick = { }, onRegisterClick = { navController.navigate(ScreenRoute.Register) })
        }

//        composable("forgotPassword") {
//            ForgotPasswordScreen(
//                onBackClick = { navController.popBackStack() }
//            )
//        }

        composable<ScreenRoute.Register> {
            UserScreen(isRegistering = true, onSaveUser = {
                userController.save(it)
                navController.navigate(ScreenRoute.Events) {
                    loggedUserState.value
                    popUpTo(ScreenRoute.Register) { inclusive = true }
                }
            }, onGoToLogin = { navController.popBackStack() })
        }

        composable<ScreenRoute.User> {
            UserScreen(onSaveUser = {
                userController.save(it)
                navController.navigate(ScreenRoute.Events)
            }, onLogout = {
                UserController.loggedUser = null
                loggedUserState.value = null
                navController.navigate(ScreenRoute.Login) {
                    popUpTo(ScreenRoute.User) { inclusive = true }
                }
            })
        }

        composable<ScreenRoute.Events> {
            EventsScreen(onEventClick = {
                if (UserController.loggedUser?.admin == true) {
                    navController.navigate(ScreenRoute.EditEvent(it))
                } else {
                    navController.navigate(ScreenRoute.Event(it))
                }
            }, onAddEventClick = { navController.navigate(ScreenRoute.EditEvent("")) })
        }

        composable<ScreenRoute.Event> {
            val eventRoute = it.toRoute<ScreenRoute.Event>()
            var event by remember { mutableStateOf(EventModel()) }

            LaunchedEffect(eventRoute.eventId) {
                event = eventController.storage.value[eventRoute.eventId] ?: EventModel()
            }

            EventScreen(event = event, onAddToCartClick = { locality, quantity ->
                val cartItem = CartItemModel(
                    userId = UserController.loggedUser?.id!!, event = event, locality = locality, quantity = quantity
                )
                cartItemController.save(cartItem)
                navController.navigate(ScreenRoute.Cart)
            }, onEditEventClick = { navController.navigate(ScreenRoute.EditEvent(event.id)) }, onDeleteEventClick = {
                eventController.delete(event.id)
                navController.popBackStack()
            })
        }

        composable<ScreenRoute.Cart> {
            CartScreen(userId = UserController.loggedUser?.id ?: "", onCheckout = { purchaseOrder ->
                if (purchaseOrder.cartItems.isNotEmpty()) {
                    purchaseOrderController.save(purchaseOrder)
                    purchaseOrder.cartItems.forEach { cartItemController.delete(it.id) }
                    navController.navigate(ScreenRoute.History)
                }
            })
        }

        composable<ScreenRoute.History> {
            HistoryScreen(userId = UserController.loggedUser?.id ?: "")
        }

        composable<ScreenRoute.EditEvent> {
            val route = it.toRoute<ScreenRoute.EditEvent>()
            var event by remember { mutableStateOf(EventModel()) }

            LaunchedEffect(route.eventId) {
                event = eventController.getById(route.eventId) ?: EventModel()
            }

            EditEventScreen(event = event, onSaveEvent = { updatedEvent ->
                eventController.save(updatedEvent)
                navController.popBackStack()
            }, onDeleteEvent = {
                eventController.delete(event.id)
                navController.popBackStack()
            }, onDiscardChanges = { navController.popBackStack() })
        }

        composable<ScreenRoute.Message> {
            val messageRoute = it.toRoute<ScreenRoute.Message>()
            MessageScreen(messageRoute.text)
        }
    }
}