package com.neepan.testfcm.auth.data.repository

import com.neepan.testfcm.auth.data.dto.UserDto
import com.neepan.testfcm.auth.data.mapper.toDomainModel
import com.neepan.testfcm.auth.domain.model.User
import com.neepan.testfcm.auth.domain.repository.AuthRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put


class AuthRepositoryImpl(
    private val supabase: SupabaseClient //we pass the supabase client in here
): AuthRepository {

    override suspend fun signUpWithUsername(username: String, password: String) : Result<User>{
        return try{
            // 1. the Hack: Create the dummy email
            val dummyEmail = "$username@test.com"

            // 2. Call supabase
            supabase.auth.signUpWith(Email){
                email = dummyEmail
                this.password = password
                data = buildJsonObject {
                    put("raw_password",username) //this triggers your sql to populate the public 'users' table
                }
            }

            // 3. if successful, get the user ID from the current session
            val sessionUser = supabase.auth.currentSessionOrNull()?.user

            if(sessionUser != null){
                // Return the pure Domain Model
                Result.success(User(id = sessionUser.id, username = username))
            }
            else{
                Result.failure(Exception("Sign up succeeded, but user session is null."))
            }

        }catch(e: Exception){

            Result.failure(Exception("Failed to sign up: ${e.localizedMessage}"))
        }
    }

    override suspend fun loginWithUsername(username: String, password: String): Result<User> {
        return try{
            val dummyEmail = "$username@test.com"

            //Log in with the dummy email
            supabase.auth.signInWith(Email){
                email = dummyEmail
                this.password = password
            }

            val sessionUser = supabase.auth.currentSessionOrNull()?.user?: throw Exception("No user found after login")

            //We can extract the username directly from the metadata supabase returns
            val savedUsername = sessionUser.userMetadata?.get("raw_username")?.toString()?.replace("\"", "")
                ?: username

            Result.success(User(id = sessionUser.id, username = username))

        }catch (e: Exception){
            Result.failure(Exception("Invalid username or password."))
        }
    }

    override suspend fun getCurrentUser(): Result<User> {
        return try {
            // 1. Check if Supabase has an active, secure session saved on the device
            val sessionUser = supabase.auth.currentSessionOrNull()?.user
                ?: throw Exception("No active session found. User needs to log in.")

            // 2. Fetch their public profile data from your 'users' table
            val userDto = supabase.postgrest["users"]
                .select { filter { eq("id", sessionUser.id) } }
                .decodeSingle<UserDto>()

            // 3. Use the Mapper to convert the raw DTO into your pure Domain Model
            Result.success(userDto.toDomainModel())

        } catch (e: Exception) {
            // If anything fails (no session, network error), we return a failure
            Result.failure(Exception("Could not fetch current user: ${e.localizedMessage}"))
        }
    }



}