package com.example.eduardoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

open class DebugActivity : AppCompatActivity() {

    private val TAG = "LMSApp"
    private val className: String
        get() {
            var s = javaClass.name
            return s.substring(s.lastIndexOf("."))
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "$className onCreate() executado!")
    }

    override fun onStart(){
        super.onStart()
        Log.d(TAG, "$className onStart() executado!")

    }
    override fun onResume(){
        super.onResume()
        Log.d(TAG, "$className onResume() executado!")

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "$className onPause() executado!")

    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "$className onRestart() executado!")

    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "$className onStop() executado!")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "$className onDestroy() executado!")

    }

}