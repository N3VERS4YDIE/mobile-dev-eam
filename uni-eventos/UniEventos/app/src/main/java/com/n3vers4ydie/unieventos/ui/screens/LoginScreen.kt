package com.n3vers4ydie.unieventos.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.n3vers4ydie.unieventos.ui.theme.UniEventosTheme

@Preview
@Composable
fun LoginScreenPreview() {
    UniEventosTheme {
        Surface {
            LoginScreen(onLoginClick = { _, _ -> }, onForgotPasswordClick = {}, onRegisterClick = {})
        }
    }
}

@Composable
fun LoginScreen(
    onLoginClick: (email: String, password: String) -> Unit,
    onForgotPasswordClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(value = email, onValueChange = {
            email = it
        }, label = { Text("Correo Electrónico") }, modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onLoginClick(email, password) }, modifier = Modifier.fillMaxWidth()) {
            Text("Iniciar Sesión")
        }

        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = onForgotPasswordClick) {
            Text("¿Olvidaste tu contraseña?")
        }

        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = onRegisterClick) {
            Text("¿No tienes una cuenta? Registrarse")
        }
    }
}
