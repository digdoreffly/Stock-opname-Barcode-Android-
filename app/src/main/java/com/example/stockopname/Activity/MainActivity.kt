package com.example.stockopname.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.stockopname.Api.ApiConfig
import com.example.stockopname.Api.ResponseLogin
import com.example.stockopname.Storage.SharedPrefManager
import com.example.stockopname.databinding.ActivityMainBinding
import okhttp3.Headers
import org.apache.poi.ss.usermodel.Header
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    var username: String? = null
    var password: String? = null

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnlogin.setOnClickListener {
            val username = binding.edtuserid.text.toString().trim()
            val password = binding.edtpass.text.toString().trim()

            if(username.isEmpty()){
                binding.edtuserid .error = "Username required"
                binding.edtuserid.requestFocus()
                return@setOnClickListener
            }

            if(password.isEmpty()){
                binding.edtpass.error = "Password required"
                binding.edtpass.requestFocus()
                return@setOnClickListener
            }
            ApiConfig.getService().login(mapOf("username" to username, "password" to password))
                .enqueue(object : Callback<ResponseLogin> {
                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    Log.e("API Error", t.message, t)
                }

                override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                    if (response.isSuccessful) {
                        val loginResponse = response.body()
                        if (loginResponse != null && !loginResponse.status?.error!!) {
                            SharedPrefManager.getInstance(applicationContext).saveUser(loginResponse.session!!)
                            val intent = Intent(applicationContext, HalamanActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                        } else {
                             Toast.makeText(applicationContext, "Login failed", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        //Handle kesalahan respons HTTP
                       Toast.makeText(applicationContext, "HTTP error: ${response.code()}", Toast.LENGTH_LONG).show()



                    }
                }
            })
        }
        }
    override fun onStart() {
        super.onStart()

        if(SharedPrefManager.getInstance(this).isLoggedIn){
            val intent = Intent(applicationContext, HalamanActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        }
    }
    }





