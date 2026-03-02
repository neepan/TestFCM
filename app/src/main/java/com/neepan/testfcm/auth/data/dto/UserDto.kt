package com.neepan.testfcm.auth.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto (
    @SerialName("id")
    val id: String,

    @SerialName("username")
    val rawUsername: String,

    @SerialName("Created_at")
    val createdAt: String
)