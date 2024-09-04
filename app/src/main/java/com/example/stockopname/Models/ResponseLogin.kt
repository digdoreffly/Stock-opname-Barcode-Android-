package com.example.stockopname.Api

import com.example.stockopname.Models.Session
import com.example.stockopname.Models.StatusResponse
import com.google.gson.annotations.SerializedName


data class ResponseLogin(
    @SerializedName("session")
    val session: Session? = null,

    @SerializedName("status")
    val status: StatusResponse? = null
//
//    @SerializedName("error")
//    val error: Boolean? = null


)


