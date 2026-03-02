package com.neepan.testfcm.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neepan.testfcm.auth.domain.usecase.LoginWithUsernameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


// Ordering food from Kitchen(Hilt)

@HiltViewModel // Tells Hilt this is a viewModel it needs to manage
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginWithUsernameUseCase // Hilt Inject this automatically
) : ViewModel(){

    fun login(username:String, password: String){
        viewModelScope.launch{
            // We can now call the Use Case Safely!

            val result = loginUseCase(username, password)

            result.onSuccess { user ->
                //handle success (e.g navigate to home screen)
                println("Success! welcome $user.username")
            }.onFailure { error ->
                // Handle failure (eg show error message on UI)
                println("Error: ${error.message}")
            }
        }
    }
}
