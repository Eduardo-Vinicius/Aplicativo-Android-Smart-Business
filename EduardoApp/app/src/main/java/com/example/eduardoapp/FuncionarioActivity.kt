package com.example.eduardoapp

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_funcionario.*
import kotlinx.android.synthetic.main.toolbar.*

class FuncionarioActivity : DebugActivity() {
    private val context: Context get() = this
    var funcionario: Funcionario? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_funcionario)

        // recuperar onjeto de Disciplina da Intent


        funcionario = intent.getSerializableExtra("disciplina") as Funcionario

        Toast.makeText(context, "Clicou no Funcionário: ${funcionario}", Toast.LENGTH_SHORT)
            .show()
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
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main_funcionario, menu)
        return true
    }
    private fun taskExcluir() {
        if (this.funcionario != null && this.funcionario is Funcionario) {
            Log.d("Excluir User", this.funcionario.toString())
                // Thread para remover a disciplina
            Thread {
                FuncionarioService.delete(this.funcionario as Funcionario)
                runOnUiThread {
                // após remover, voltar para activity anterior
                    finish()
                }
            }.start()
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
// id do item clicado
        val id = item?.itemId
// verificar qual item foi clicado
// remover a disciplina no WS
        if (id == R.id.action_remover) {
// alerta para confirmar a remoção
// só remove se houver confirmação positiva
            AlertDialog.Builder(this)

                .setTitle(R.string.app_name)
                .setMessage("Deseja excluir a disciplina")
                .setPositiveButton("Sim") {
                        dialog, which ->
                    dialog.dismiss()
                    taskExcluir()

                }.setNegativeButton("Não") {
                        dialog, which -> dialog.dismiss()
                }.create().show()

        }
        // botão up navigation
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
