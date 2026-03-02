package com.neepan.testfcm.auth.data.mapper

import com.neepan.testfcm.auth.data.dto.UserDto
import com.neepan.testfcm.auth.domain.model.User

fun UserDto.toDomainModel(): User {
    return User(
        id= this.id,
        username = this.rawUsername
    )
}