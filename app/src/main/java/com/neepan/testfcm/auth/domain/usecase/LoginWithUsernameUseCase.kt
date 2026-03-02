package com.neepan.testfcm.auth.domain.usecase

import com.neepan.testfcm.auth.domain.model.User
import com.neepan.testfcm.auth.domain.repository.AuthRepository

class LoginWithUsernameUseCase(
    private val repository: AuthRepository //we pass the interface not the implementation
) {
    //using 'operator fun invoke' allows us to call this class like a regular function
    suspend operator fun invoke(username: String, password: String): Result<User> {

        //pure Business Logic & Validation
        val cleanUsername = username.trim()

        if (cleanUsername.isBlank()) {
            return Result.failure(Exception("Username cannot be empty"))
        }

        if(password.length<6){
            return Result.failure(Exception("Password must be at least 6 characters long"))
        }

        // 2. If validation passes, hand it off to the Repository contract
        return repository.loginWithUsername(cleanUsername,password)
    }
}