package com.egobank.domain.model

data class Transaction(
    val id: String,
    val description: String,
    val amount: Double,
    val date: String
)