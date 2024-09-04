package com.example.stockopname.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stockopname.Adapter.HistoryAdapter
import com.example.stockopname.Api.ApiConfig
import com.example.stockopname.Api.ApiService
import com.example.stockopname.Models.DataItem
import com.example.stockopname.Models.GetHistory
import com.example.stockopname.Storage.SharedPrefManager
import com.example.stockopname.databinding.ActivityHistoryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HistoryActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPrefManager = SharedPrefManager.getInstance(this)
        val session = sharedPrefManager.session
        val level = session.level
        val layoutManager = LinearLayoutManager(this)
        binding.rvHistory.layoutManager = layoutManager
        if (SharedPrefManager.getInstance(this).isLoggedIn) {

//            // Mendapatkan token dari SharedPrefManager
//            val token = SharedPrefManager.getInstance(this).getToken()
//
//            // Gunakan token saat membuat permintaan API
//            val apiService = ApiService.create(token)

            ApiConfig.getService().getHistory().enqueue(object : Callback<GetHistory> {
                override fun onResponse(call: Call<GetHistory>, response: Response<GetHistory>) {
                    if (response.isSuccessful) {
                        val getHistory = response.body()
                        if (getHistory != null) {
                            // Access the list of DataItem
                            val historyItems: List<DataItem?>?  = getHistory.data


                            // Gunakan adapter untuk menampilkan data riwayat
                            val historyAdapter = HistoryAdapter(this@HistoryActivity, historyItems.orEmpty())
                            binding.rvHistory.adapter = historyAdapter
                        } else {
                            // Response body is null
                            // Handle accordingly
                        }
                    } else {
                        // Response not successful
                        // Handle accordingly
                    }
                }

                override fun onFailure(call: Call<GetHistory>, t: Throwable) {
                    // Handle failure (e.g., network error, etc.)
                }
            })

        }
    }
}
