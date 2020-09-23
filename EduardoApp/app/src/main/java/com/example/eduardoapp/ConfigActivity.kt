package com.example.eduardoapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast

class ConfigActivity : DebugActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.config)
        supportActionBar?.title = "Jaba";
        supportActionBar?.setDisplayHomeAsUpEnabled(true)





    }



}