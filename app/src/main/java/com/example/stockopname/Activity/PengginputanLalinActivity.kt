package com.example.stockopname.Activity

import android.app.Activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.stockopname.Api.ApiConfig
import com.example.stockopname.Models.GetDataBarang
import com.example.stockopname.Models.ResponseInsertMasuk
import com.example.stockopname.Storage.SharedPrefManager
import com.example.stockopname.databinding.ActivityPenginputanLalinBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PengginputanLalinActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityPenginputanLalinBinding
    private val  BARCODE_SCANNER_REQUEST_CODE = 123
    private lateinit var sharedPrefManager: SharedPrefManager
    private lateinit var ETIdbarang :EditText
    var barcodeResult : String? = null
    var username : String? = null
    var nomerbarang : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPenginputanLalinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPrefManager = SharedPrefManager.getInstance(this)



    }

     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         super.onActivityResult(requestCode, resultCode, data)

         if (requestCode == BARCODE_SCANNER_REQUEST_CODE) {
             if (resultCode == Activity.RESULT_OK) {
                 barcodeResult = data?.getStringExtra("barcodeResult")
//                 ETIdbarang = binding.ETIdbarang
//                 binding.ETIdbarang.setText(barcodeResult)


                 if  (SharedPrefManager.getInstance(this).isLoggedIn) {
                     val session =sharedPrefManager.session
                     val username =  session.email

                     barcodeResult?.let {
                         val params = mapOf("id_barang" to barcodeResult.orEmpty())
                         ApiConfig.getService().getBarang(params)
                             .enqueue(object : Callback<GetDataBarang> {
                                 override fun onResponse(
                                     call: Call<GetDataBarang>,
                                     response: Response<GetDataBarang>
                                 ) {
                                     // Handle response di sini
                                     if (response.isSuccessful) {
                                         val dataBarang = response.body()
                                         if (dataBarang != null && !dataBarang.error && dataBarang.data.isNotEmpty()) {
                                             // Ambil beberapa data barang
                                             val firstBarang = dataBarang.data[0]

                                             nomerbarang = firstBarang.pkBarangId
                                             binding.ETNomorBarang.setText(nomerbarang)

                                             val idBarang = firstBarang.idBarang
                                             binding.ETIdbarang.setText(idBarang)

                                             val namaBarang = firstBarang.namaBarang
                                             binding.ETNamaBarang.setText(namaBarang)

                                             // Lakukan tindakan dengan data barang yang diambil
                                             // Misalnya, tampilkan data di log atau update UI
                                             Log.d(
                                                 "DataBarang",
                                                 "pkBarangId: , idBarang: $idBarang, namaBarang: $namaBarang"
                                             )
                                         } else {
                                             // Response body is null or error or data is empty
                                         }
                                     } else {
                                         // Response not successful
                                     }
                                 }

                                 override fun onFailure(call: Call<GetDataBarang>, t: Throwable) {
                                     Toast.makeText(
                                         applicationContext,
                                         t.message,
                                         Toast.LENGTH_LONG
                                     ).show()
                                     Log.e("API Error", t.message, t)

                                 }
                             })
                     }
                 }
             }
         }
     }

    fun addQuantity(view: View){
    val Quantity = binding.ETQuantity.text.toString().trim()
         val requestBody = mapOf(
                "id_barang" to nomerbarang.orEmpty(),
                "jumlah_masuk" to Quantity
            )

            ApiConfig.getService().insertMasuk(requestBody)
                .enqueue(object : Callback<ResponseInsertMasuk> {
                    override fun onResponse(
                        call: Call<ResponseInsertMasuk>,
                        response: Response<ResponseInsertMasuk>
                    ) {
                        if (response.isSuccessful) {
                            val insertMasukResponse = response.body()
                            if (insertMasukResponse != null) {
                                // Handle response data if needed
                                println("Status: ${insertMasukResponse.status}")
                                println("Error: ${insertMasukResponse.error}")
                                val intent = Intent(view.context, HalamanActivity::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                view.context.startActivity(intent)
                            }
                        } else {
                            // Handle error
                            println("Error: ${response.code()}")
                        }
                    }

                    override fun onFailure(call: Call<ResponseInsertMasuk>, t: Throwable) {
                        // Handle failure
                        t.printStackTrace()
                        println("Error: ${t.message}")
                    }
                })

    }



    fun scanbtn(view: View) {
        doresult(123, "InputTag")

    }
    fun doresult(code: Int, namacode: String) {
        val intent = Intent(this@PengginputanLalinActivity, BarcodeScannerActivity::class.java)
        intent.putExtra("barcodeResult", namacode)
        startActivityForResult(intent, BARCODE_SCANNER_REQUEST_CODE)
    }
}
