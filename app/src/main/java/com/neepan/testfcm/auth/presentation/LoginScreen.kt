package com.neepan.testfcm.auth.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel


// 1. The "Smart" Screen: This is what MainActivity calls. It connects to Hilt.
@Composable
fun LoginScreen(
    viewModel: AuthViewModel = hiltViewModel()
) {
    // We pass the ViewModel's function down into the Dumb screen
    LoginScreenContent(
        onLoginClick = { username, password ->
            viewModel.login(username, password)
        }
    )
}

// 2. The "Dumb" Screen: This ONLY draws UI. It knows nothing about ViewModels or Hilt!
@Composable
fun LoginScreenContent(
    onLoginClick: (String, String) -> Unit // A simple function passed in as a parameter
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Welcome to Dustibun", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            // When clicked, it triggers the function we passed in
            onClick = { onLoginClick(username, password) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }
    }
}