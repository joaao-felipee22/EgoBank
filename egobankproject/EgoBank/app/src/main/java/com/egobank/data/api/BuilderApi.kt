package com.egobank.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofit = Retrofit.Builder()
    .baseUrl("https://api.egobank.com/") //Armazenar base url em local mais seguro
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val api = retrofit.create(EgoBankApi::class.java)