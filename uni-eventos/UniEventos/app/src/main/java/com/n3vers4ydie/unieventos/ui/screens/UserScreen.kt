package com.n3vers4ydie.unieventos.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.n3vers4ydie.unieventos.Global
import com.n3vers4ydie.unieventos.controllers.UserController
import com.n3vers4ydie.unieventos.models.UserModel

@Composable
fun UserScreen(
    isRegistering: Boolean = false,
    onSaveUser: (UserModel) -> Unit,
    onLogout: () -> Unit = {},
    onGoToLogin: () -> Unit = {},
) {
    var dni by remember { mutableStateOf(UserController.loggedUser?.dni ?: "") }
    var name by remember { mutableStateOf(UserController.loggedUser?.name ?: "") }
    var address by remember { mutableStateOf(UserController.loggedUser?.address ?: "") }
    var phone by remember { mutableStateOf(UserController.loggedUser?.phone ?: "") }
    var email by remember { mutableStateOf(UserController.loggedUser?.email ?: "") }
    var password by remember { mutableStateOf(UserController.loggedUser?.password ?: "") }

    fun saveUser() {
        val user = UserModel(
            id = UserController.loggedUser?.id ?: "",
            dni = dni,
            name = name,
            email = email,
            password = password,
            phone = phone,
            address = address,
            admin = false
        )

        onSaveUser(user)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(Global.innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(Global.paddingValue),
            ) {
                Image(
                    Icons.Default.Person,
                    contentDescription = "Imagen de usuario",
                    modifier = Modifier.size(150.dp),
                    colorFilter = ColorFilter.tint(Color.Gray)
                )

                OutlinedTextField(
                    value = dni,
                    onValueChange = { dni = it },
                    label = { Text("DNI") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre Completo") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    label = { Text("Dirección de Residencia") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Teléfono") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Correo Electrónico") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation()
                )
            }

            if (isRegistering) {
                Button(
                    onClick = { saveUser() }, modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Registrar")
                }

                Spacer(modifier = Modifier.height(8.dp))

                TextButton(onClick = onGoToLogin) {
                    Text("¿Ya tienes una cuenta? Iniciar sesión")
                }
            } else {
                Button(
                    onClick = { saveUser() }, modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Guardar Cambios")
                }
            }

        }

        FloatingActionButton(
            onClick = { onLogout() }, modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(Global.paddingValue)
        ) {
            Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = "Cerrar sesión")
        }
    }

}