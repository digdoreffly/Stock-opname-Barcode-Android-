package com.example.stockopname.Models

import com.google.gson.annotations.SerializedName

data class ResponseInsertMasuk (
    @SerializedName("status")
    val status: String? = null,

    @SerializedName("error")
    val error: Boolean = false ,
)
