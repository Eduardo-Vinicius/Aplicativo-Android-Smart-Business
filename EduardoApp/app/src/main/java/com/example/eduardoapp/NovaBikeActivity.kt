package com.example.eduardoapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_adicionar_bike.*
import kotlinx.android.synthetic.main.activity_adicionar_funcionario.*
import kotlinx.android.synthetic.main.toolbar.*

class NovaBikeActivity : DebugActivity() {
    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_bike)
        setTitle("Nova Bike")

        btnCadastrarBike.setOnClickListener {
            val bike = Bicicleta()
            bike.nome = txtBicicleta.text.toString()
            bike.cor = txtCor.text.toString()
// "https://image.flaticon.com/icons/png/512/2282/2282560.png"
            bike.foto = txtFotoBike.text.toString()

            taskAtualizar(bike)
        }
    }

    private fun taskAtualizar(disciplina: Bicicleta) {
        // Thread para salvar a discilpina
        Thread {
            BicicletaService.save(disciplina)
            runOnUiThread {
                // após cadastrar, voltar para activity anterior
                finish()
            }
        }.start()
    }
}