package com.example.stockopname.Api

 import com.example.stockopname.Models.GetDataBarang
 import com.example.stockopname.Models.GetHistory
 import com.example.stockopname.Models.ResponseInsertMasuk
 import retrofit2.Call
 import retrofit2.http.*

interface ApiService {
   // @FormUrlEncoded
    @POST("login")
    fun login(
       @Body body : Map<String, @JvmSuppressWildcards Any>,
    ): Call<ResponseLogin>

    @POST("get_barang")
    fun getBarang(
    @Body body : Map<String, @JvmSuppressWildcards Any>,
    ): Call<GetDataBarang>

    @POST("insert_masuk")
    fun insertMasuk(
     @Body body : Map<String, @JvmSuppressWildcards Any>,
    ): Call<ResponseInsertMasuk>


   @GET("get_history")
   fun getHistory(): Call<GetHistory>

}