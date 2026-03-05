package com.egobank.domain.repository

import com.egobank.domain.model.Transaction

interface TransactionRepository {
    suspend fun getStatement(): Result<List<Transaction>>
}