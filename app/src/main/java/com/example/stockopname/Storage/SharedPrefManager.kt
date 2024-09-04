package com.example.stockopname.Storage

import android.content.Context
import com.example.stockopname.Models.Session

class SharedPrefManager private constructor(private val mCtx: Context) {
    val isLoggedIn: Boolean
    get() {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.contains("username")
    }

    val session: Session
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return Session(
                sharedPreferences.getString("user_id", null),
                sharedPreferences.getString("level", null),
                sharedPreferences.getString("username", null),
                sharedPreferences.getString("email", null),


            )
        }


    fun saveUser(session: Session) {

        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("user_id", session.userId)
        editor.putString("level", session.level)
        editor.putString("username", session.username)
        editor.putString("email", session.email)

        editor.apply()

    }


    fun clear() {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }


    companion object {
        private val SHARED_PREF_NAME = "my_shared_preff"
        private val COOKIE_KEY = "user_cookies"
        private var mInstance: SharedPrefManager? = null
        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance as SharedPrefManager
        }
    }
}