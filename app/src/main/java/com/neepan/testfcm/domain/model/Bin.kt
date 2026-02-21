package com.neepan.testfcm.domain.model

data class Bin(
    val uuid: Int,
    val binName: String,
    val maxHeight: Float,
    val currentHeight: Float,
    val isOnline: Boolean,
    val lastAssigned: Int,
    val createdAt: String?
)