package com.egobank.usecase

import com.egobank.domain.repository.TransactionRepository

class GetStatementUseCase(private val repository: TransactionRepository) {
    suspend operator fun invoke() = repository.getStatement()
}