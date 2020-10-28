package com.example.eduardoapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_adicionar_funcionario.*
import kotlinx.android.synthetic.main.toolbar.*

class NovoFuncionarioActivity : DebugActivity() {
    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_funcionario)
        setTitle("Nova Disciplina")

        btnCadastrarFunc.setOnClickListener {
            val disciplina = Funcionario()
            disciplina.nome = txtFuncionario.text.toString()
            disciplina.cargo = txtEmenta.text.toString()
// "https://image.flaticon.com/icons/png/512/2282/2282560.png"
            disciplina.foto = txtFoto.text.toString()

            taskAtualizar(disciplina)
        }
    }

    private fun taskAtualizar(disciplina: Funcionario) {
        // Thread para salvar a discilpina
        Thread {
            FuncionarioService.save(disciplina)
            runOnUiThread {
                // após cadastrar, voltar para activity anterior
                finish()
            }
        }.start()
    }
}