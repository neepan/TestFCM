package com.neepan.testfcm.domain.repository

import com.neepan.testfcm.domain.model.User

interface AuthRepository{

    suspend fun signUpWithUsername(username: String, password: String): Result<User>

    suspend fun loginWithUsername(username: String, password: String): Result<User>

    suspend fun getCurrentUser(): Result<User>
}