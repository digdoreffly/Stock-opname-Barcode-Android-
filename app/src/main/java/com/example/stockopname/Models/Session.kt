package com.example.stockopname.Models

import com.google.gson.annotations.SerializedName

data class Session(
    @SerializedName("user_id")
    val userId: String? = null,

    @SerializedName("level")
    val level: String? = null,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("username")
    val username: String? = null
)