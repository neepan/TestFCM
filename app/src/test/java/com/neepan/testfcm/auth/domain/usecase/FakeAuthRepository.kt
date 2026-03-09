package com.neepan.testfcm.auth.domain.usecase

import com.neepan.testfcm.auth.domain.model.User
import com.neepan.testfcm.auth.domain.repository.AuthRepository

class FakeAuthRepository: AuthRepository {


    override suspend fun signUpWithUsername(username: String, password: String): Result<User> {
        // Just instantly return a fake successful user
        return Result.success(User(id="fake_id_123",username=username))
    }

    override suspend fun loginWithUsername(username: String, password: String): Result<User> {
        // Just instantly return a fake successful user

        return Result.success(User(id = "fake_id_123", username = username))
    }


    override suspend fun getCurrentUser(): Result<User> {
        return Result.success(User(id = "fake_id_123", username = "test_user"))
    }

}