package com.example.retrofitkotlin.service

import com.example.retrofitkotlin.model.CryptoModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface CryptoService {
    //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json
    //with coroutines
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    suspend fun getData(): Response<List<CryptoModel>>


}