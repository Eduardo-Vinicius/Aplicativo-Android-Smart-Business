package com.example.eduardoapp

import android.os.Bundle
import android.os.Debug
import kotlinx.android.synthetic.main.toolbar.*

class AdicionarFuncActivity : DebugActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_funcionario)

        val args = intent.extras
        val nome = args?.getString("nome_tela")

        supportActionBar?.title = nome;

    }
}