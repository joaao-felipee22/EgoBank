package com.egobank.data.remote

import com.egobank.domain.model.Transaction
import com.google.gson.annotations.SerializedName

data class TransactionResponse(
    @SerializedName("transaction_id") val id: String,
    @SerializedName("label") val description: String,
    @SerializedName("value_cents") val amountCents: Int, // API manda em centavos
    val date: String
)

// Função de Extensão (Mapper)
fun TransactionResponse.toDomain() = Transaction(
    id = this.id,
    description = this.description,
    amount = this.amountCents / 100.0, // Conversão de lógica simples
    date = this.date
)