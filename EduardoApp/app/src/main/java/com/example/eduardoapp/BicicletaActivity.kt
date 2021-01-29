package com.example.eduardoapp

import android.os.Bundle
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_bicicleta.*
import kotlinx.android.synthetic.main.toolbar.*

class BicicletaActivity: DebugActivity() {
    var bicicleta: Bicicleta? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bicicleta)
// recuperar onjeto de Disciplina da Intent
        bicicleta = intent.getSerializableExtra("disciplina") as Bicicleta
// configurar título com nome da Disciplina e botão de voltar da Toolbar
// colocar toolbar
        setSupportActionBar(toolbar)
// alterar título da ActionBar
        supportActionBar?.title = bicicleta?.nome
// up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
// atualizar dados do carro
        nomeBicicleta.text = bicicleta?.nome
        corBicicleta.text = bicicleta?.cor
        Picasso.with(this).load(bicicleta?.foto).fit().into(imagemDisciplina,

            object: com.squareup.picasso.Callback{
                override fun onSuccess() {}
                override fun onError() { }
            })

    }
}