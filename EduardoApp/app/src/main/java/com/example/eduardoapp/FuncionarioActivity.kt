package com.example.eduardoapp

import android.os.Bundle
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_funcionario.*
import kotlinx.android.synthetic.main.toolbar.*

class FuncionarioActivity : DebugActivity() {

    var funcionario: Funcionario? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_funcionario)

        // recuperar onjeto de Disciplina da Intent
        funcionario = intent.getSerializableExtra("funcionario") as Funcionario
        Toast.makeText(this, "$funcionario", Toast.LENGTH_LONG).show()
        // configurar título com nome da Disciplina e botão de voltar da Toolbar
        // colocar toolbar

        setSupportActionBar(toolbar)
        // alterar título da ActionBar
        supportActionBar?.title = funcionario?.nome
        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // atualizar dados do carro
        nomeFuncionario.text = funcionario?.nome
        Picasso.with(this).load(funcionario?.foto).fit().into(imagemDisciplina,

            object: com.squareup.picasso.Callback{
                override fun onSuccess() {}
                override fun onError() { }
            })

    }
    }
