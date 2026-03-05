package com.example.egobank

import com.egobank.data.remote.TransactionResponse
import com.egobank.domain.repository.TransactionRepositoryImpl
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

class TransactionRepositoryImplTest {

    // 1. Mocks: Simulamos a API para não depender de internet no teste
    private val api: egobank = mockk()

    // 2. SUT (System Under Test): A classe real que estamos testando
    private lateinit var repository: TransactionRepositoryImpl

    @Before
    fun setup() {
        repository = TransactionRepositoryImpl(api)
    }

    @Test
    fun `when fetchStatement returns success, then should map to domain models`() = runTest {
        // ARRANGE (Preparar)
        val apiResponse = listOf(
            TransactionResponse(
                id = "1",
                description = "Pix",
                amountCents = 10000,
                date = "2026-03-05"
            )
        )
        coEvery { api.fetchStatement() } returns apiResponse

        // ACT (Agir)
        val result = repository.getStatement()

        // ASSERT (Verificar)
        assertTrue(result.isSuccess)
        val transactions = result.getOrNull()
        assertEquals(1, transactions?.size)
        assertEquals(100.0, transactions?.first()?.amount) // Verifica se o Mapper dividiu por 100
        assertEquals("Pix", transactions?.first()?.description)
    }

    @Test
    fun `when api throws exception, then should return failure result`() = runTest {
        // ARRANGE
        coEvery { api.fetchStatement() } throws Exception("Network Error")

        // ACT
        val result = repository.getStatement()

        // ASSERT
        assertTrue(result.isFailure)
        assertEquals("Network Error", result.exceptionOrNull()?.message)
    }
}