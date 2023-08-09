package com.example.retrofitkotlin.model

import com.google.gson.annotations.SerializedName
import java.util.Currency

data class CryptoModel(
    //  @SerializedName("currency")
    val currency: String,
    // @SerializedName("price")
    val price: String
)
