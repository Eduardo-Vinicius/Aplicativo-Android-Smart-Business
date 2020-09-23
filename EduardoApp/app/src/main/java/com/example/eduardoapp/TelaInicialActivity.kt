package com.example.eduardoapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.login_constraint.*


class TelaInicialActivity : DebugActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)



        val args = intent.extras
        val nome = args?.getString("nome_usuario")
        //val num = args?.getInt("numero")


        cadastroBicicleta.setOnClickListener() {

            var intent = Intent(this, CadastroActivity::class.java)
            intent.putExtra("nome_tela", "Cadastro de Bicicleta")
            startActivityForResult(intent, 0)



        }
        cadastroCliente.setOnClickListener() {

            var intent = Intent(this, CadastroActivity::class.java)
            intent.putExtra("nome_tela", "Cadastro de Cliente")
            startActivityForResult(intent, 0)




        }

        cadastroUsuario.setOnClickListener() {

            var intent = Intent(this, CadastroActivity::class.java)
            intent.putExtra("nome_tela", "Cadastro de Usuário")
            startActivityForResult(intent, 0)

        }

        // Toast.makeText(this,"Usuário: $nome", Toast.LENGTH_LONG).show()


         setSupportActionBar(toolbar)

        supportActionBar?.title = "Bike Na Porta"
         supportActionBar?.setDisplayHomeAsUpEnabled(true)




    }
    fun cliqueSair() {
        val returnIntent = Intent();
        returnIntent.putExtra("result","Saída do BrewerApp");
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main, menu)
        // vincular evento de buscar
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView?)?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                // ação enquanto está digitando
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // ação  quando terminou de buscar e enviou
                return false
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // id do item clicado
        val id = item?.itemId
        // verificar qual item foi clicado e mostrar a mensagem Toast na tela
        // a comparação é feita com o recurso de id definido no xml
        if  (id == R.id.action_buscar) {
            Toast.makeText(this, "Botão de buscar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_atualizar) {
            progressBar.visibility = View.VISIBLE
            Handler().postDelayed(
                {
                    progressBar.visibility = View.GONE

                    Toast.makeText(this, "Atualizando conteúdo da tela...", Toast.LENGTH_LONG).show()
                    
                }, 10000
            )
            Toast.makeText(this, "Atualizando conteúdo da tela", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_config) {
            var intent = Intent(this, ConfigActivity::class.java)
            startActivityForResult(intent, 0)
            Toast.makeText(this, "Botão de configuracoes", Toast.LENGTH_LONG).show()
        }
        // botão up navigation
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}