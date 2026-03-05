package com.egobank.domain.repository

import com.egobank.domain.model.Transaction

class TransactionRepositoryImpl(
    private val api: EgobankApi
) : TransactionRepository {
    override suspend fun getStatement(): Result<List<Transaction>> {
        return try {
            val response = api.fetchStatement()
            Result.success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}