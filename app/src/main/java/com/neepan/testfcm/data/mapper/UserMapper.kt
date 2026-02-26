package com.neepan.testfcm.data.mapper

import com.neepan.testfcm.data.dto.UserDto
import com.neepan.testfcm.domain.model.User

fun UserDto.toDomainModel(): User {
    return User(
        id= this.id,
        username = this.rawUsername
    )
}