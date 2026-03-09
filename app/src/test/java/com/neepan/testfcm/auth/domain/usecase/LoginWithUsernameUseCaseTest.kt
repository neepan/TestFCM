package com.neepan.testfcm.auth.domain.usecase

import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Test

class LoginWithUsernameUseCaseTest {

    // We will build this FakeAuthRepository in a moment. It just mimics the interface.
    private val fakeRepository  = FakeAuthRepository()
    private val loginUseCase = LoginWithUsernameUseCase(fakeRepository)

    @Test
    fun `Empty username returns failure`()= runBlocking <Unit> {
        //1. Arrange & Act: Call the use case with a bad username

        val result = loginUseCase(username="    ",password="validPassword@123")

        // 2. Assert: Verify that the result is a failure
        assertTrue(result.isFailure)

        //optional: Verify the exact error message
        result.onFailure { error->
            assertTrue(error.message=="Username cannot be empty")
        }
    }

    @Test
    fun `Password under 6 characters returns failure`()= runBlocking {
        val result = loginUseCase(username = "validUser", password = "123")
        assertTrue(result.isFailure)
    }

}