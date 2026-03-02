package com.neepan.testfcm.auth.domain.repository

import com.neepan.testfcm.auth.domain.model.User

interface AuthRepository{

    suspend fun signUpWithUsername(username: String, password: String): Result<User>

    suspend fun loginWithUsername(username: String, password: String): Result<User>

    suspend fun getCurrentUser(): Result<User>
}