package com.example.eduardoapp

import android.os.Bundle
import kotlinx.android.synthetic.main.toolbar.*

class CadastroActivity : DebugActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cadastro)

        val args = intent.extras
        val nome = args?.getString("nome_tela")

        supportActionBar?.title = nome;

    }



}