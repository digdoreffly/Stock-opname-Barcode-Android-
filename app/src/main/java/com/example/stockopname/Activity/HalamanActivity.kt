package com.example.stockopname.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.stockopname.R
import com.example.stockopname.Storage.SharedPrefManager
import com.example.stockopname.databinding.ActivityHalamanBinding
import com.example.stockopname.databinding.ActivityMainBinding

class HalamanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHalamanBinding
    private lateinit var sharedPrefManager: SharedPrefManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHalamanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setInitLayout()
         sharedPrefManager = SharedPrefManager.getInstance(this)
        val session =sharedPrefManager.session
        val level = session.level
        val username =  session.email
        binding.containMain.TvInput.text ="Form Input $level"
        binding.containMain.TvReport.text ="Form Report $username"
        Log.d(
            "session",
        "$session")
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.optionmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        if (id == R.id.LOGOUT) {
            LOGOUT()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun LOGOUT() {
        sharedPrefManager.clear()
        
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)


        startActivity(intent)
        finish()
    }


    private fun setInitLayout() {
        binding.containMain.cvInput.setOnClickListener { v: View? ->
            val intent = Intent(this@HalamanActivity, PengginputanLalinActivity::class.java)
            startActivity(intent)
        }

        binding.containMain.cvReport.setOnClickListener { v: View? ->
            val intent = Intent(this@HalamanActivity, HistoryActivity::class.java)
            startActivity(intent)
        }
}
//    override fun onStart() {
//        super.onStart()
//
//        if(SharedPrefManager.getInstance(this).isLoggedIn){
//            val intent = Intent(applicationContext, HalamanActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//
//            startActivity(intent)
//        }
//    }
    }