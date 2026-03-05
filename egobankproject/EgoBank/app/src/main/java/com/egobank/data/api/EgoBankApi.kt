package com.egobank.data.api

import com.egobank.data.remote.TransactionResponse
import retrofit2.http.GET

interface EgoBankApi {
    @GET("pix/transactions")
    suspend fun getUserProfile(): TransactionResponse
}