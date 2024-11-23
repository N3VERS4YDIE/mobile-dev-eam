package com.n3vers4ydie.unieventos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.n3vers4ydie.unieventos.controllers.UserController
import com.n3vers4ydie.unieventos.ui.navigation.AppNavigationBar
import com.n3vers4ydie.unieventos.ui.navigation.Navigation
import com.n3vers4ydie.unieventos.ui.theme.UniEventosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UniEventosTheme {
                val navController = rememberNavController()
                var loggedUserState = remember { mutableStateOf( UserController.loggedUser) }

                LaunchedEffect(UserController.loggedUser != null) {
                    loggedUserState.value = UserController.loggedUser
                }

                Scaffold(bottomBar = {
                    if (loggedUserState.value != null) {
                        AppNavigationBar(navController, loggedUserState)
                    }
                }) { innerPadding ->
                    Global.innerPadding = PaddingValues(
                        top = 0.dp,
                        start = Global.paddingValue,
                        end = Global.paddingValue,
                        bottom = innerPadding.calculateBottomPadding()
                    )

                    Navigation(
                        navController, loggedUserState
                    )
                }
            }
        }
    }
}
