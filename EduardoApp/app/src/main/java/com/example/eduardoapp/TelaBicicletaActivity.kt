package com.example.eduardoapp

import BicicletaAdapter
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_tela_bicicleta.*
import kotlinx.android.synthetic.main.activity_tela_funcionario.*
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.activity_tela_inicial.recyclerFuncionarios
import kotlinx.android.synthetic.main.toolbar.*

class TelaBicicletaActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

        private val context: Context get() = this
        private var bicicletas = listOf<Bicicleta>()
        private var REQUEST_CADASTRO = 1
        private var REQUEST_REMOVE= 2

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_tela_bicicleta)
// código existente - omitido
// configurar cardview
            recyclerDisciplinas?.layoutManager = LinearLayoutManager(context)
            recyclerDisciplinas?.itemAnimator = DefaultItemAnimator()
            recyclerDisciplinas?.setHasFixedSize(true)
            setSupportActionBar(toolbar)
            supportActionBar?.title = "Bike Na Porta"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

        }

        override fun onResume() {
            super.onResume()
            getApplicationContext()
// task para recuperar as disciplinas
            taskDisciplinas()
        }
        fun taskDisciplinas() {
            Thread {
                bicicletas = BicicletaService.getBicicletas(context)
                runOnUiThread {
//               / atualizar lista
                    recyclerDisciplinas?.adapter =
                        BicicletaAdapter(bicicletas) { onClickDisciplina(it) }
                }


            }.start()
        }
        // tratamento do evento de clicar em uma disciplina
        fun onClickDisciplina(bicicleta: Bicicleta) {
                Toast.makeText(context, "Clicou disciplina ${bicicleta.nome}", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, BicicletaActivity::class.java)
                intent.putExtra("disciplina", bicicleta)
                startActivity(intent)
        }
    private fun configuraMenuLateral() {
        var toogle = ActionBarDrawerToggle(
            this,
            layoutMenuLateralBike,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_closed)
        layoutMenuLateral.addDrawerListener(toogle)
        toogle.syncState()
        menu_lateralbike.setNavigationItemSelectedListener(this)
    }
    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main, menu)
        // vincular evento de buscar
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView?)?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // ação  quando terminou de buscar e enviou
                return false
            }

        })
        return true
    }
    fun cliqueSair() {
        val returnIntent = Intent();
        returnIntent.putExtra("result","Saída do BikeNaPorta");
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_bicicleta -> {
                Toast.makeText(this, "Clicou Bicicletas", Toast.LENGTH_SHORT).show()
                var intent = Intent(this, CadastroActivity::class.java)
                intent.putExtra("nome_tela", "Cadastro de Bicicleta")
                startActivityForResult(intent, 0)
            }
            R.id.nav_config -> {
                Toast.makeText(this, "Clicou Configurações", Toast.LENGTH_SHORT).show()
                var intent = Intent(this, ConfigActivity::class.java)
                intent.putExtra("nome_tela", "Configurações")
                startActivityForResult(intent, 0)
            }
            R.id.nav_funcionario -> {
                var intent = Intent(this, NovoFuncionarioActivity::class.java)
                startActivityForResult(intent, REQUEST_CADASTRO)
                Toast.makeText(this, "Clicou Funcionários", Toast.LENGTH_SHORT).show()
                //var intent = Intent(this, NovoFuncionarioActivity::class.java)
                // intent.putExtra("nome_tela", "Cadastro de Funcionários")

                //startActivityForResult(intent, 0)
            }
            R.id.nav_sair -> {
                Toast.makeText(this, "Clicou em Sair", Toast.LENGTH_SHORT).show()
                cliqueSair()
            }
            R.id.nav_localizacao -> {
                val it = Intent(this, MapasActivity::class.java)
                startActivity(it)
            }


        }
        layoutMenuLateral.closeDrawer(GravityCompat.START)
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
                    taskDisciplinas()
                    Toast.makeText(this, "Atualizando conteúdo da tela...", Toast.LENGTH_LONG).show()

                }, 3000
            )
            Toast.makeText(this, "Atualizando conteúdo da tela", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_config) {
            var intent = Intent(this, ConfigActivity::class.java)
            startActivityForResult(intent, 0)
            Toast.makeText(this, "Botão de configuracoes", Toast.LENGTH_LONG).show()
        }
        else if (id == R.id.action_cadastrar) {
            var intent = Intent(this, NovaBikeActivity::class.java)
            startActivityForResult(intent, REQUEST_CADASTRO)
            Toast.makeText(this, "Botão de Adicionar", Toast.LENGTH_LONG).show()
        }
        // botão up navigation
        else if (id == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
// código existente - omitido
    }

