package com.example.eduardoapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
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
import kotlinx.android.synthetic.main.activity_tela_funcionario.*
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.login_constraint.*
import kotlinx.android.synthetic.main.activity_tela_inicial.recyclerFuncionarios as recyclerFuncionarios1


class TelaInicialActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var REQUEST_CADASTRO = 1
    private var REQUEST_REMOVE= 2
    private val context: Context get() = this
    private var users = listOf<Login>()
    private var funcionarios = listOf<Funcionario>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)
        val args = intent.extras
        val nome = args?.getString("nome_usuario")


        setSupportActionBar(toolbar)
        supportActionBar?.title = "Bike Na Porta"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        configuraMenuLateral()

        recyclerFuncionarios?.layoutManager = LinearLayoutManager(context)
        recyclerFuncionarios?.itemAnimator = DefaultItemAnimator()
        recyclerFuncionarios?.setHasFixedSize(true)

         val num = args?.getInt("numero")


//        cadastroBicicleta.setOnClickListener() {
//
//            var intent = Intent(this, CadastroActivity::class.java)
//            intent.putExtra("nome_tela", "Cadastro de Bicicleta")
//            startActivityForResult(intent, 0)
//
//
//
//        }
//        cadastroCliente.setOnClickListener() {
//
//            var intent = Intent(this, CadastroActivity::class.java)
//            intent.putExtra("nome_tela", "Cadastro de Cliente")
//            startActivityForResult(intent, 0)
//
//
//
//
//        }
//
//        cadastroUsuario.setOnClickListener() {
//
//            var intent = Intent(this, CadastroActivity::class.java)
//            intent.putExtra("nome_tela", "Cadastro de Usuário")
//            startActivityForResult(intent, 0)
//
//     }

        val nome1 = Prefs.getString("nome")
        Toast.makeText(this, nome1, Toast.LENGTH_LONG).show()


        Toast.makeText(this,"Usuário: $nome1", Toast.LENGTH_LONG).show()
}
        override fun onResume() {
            super.onResume()
    // task para recuperar as disciplinas
            taskFuncionarios()
    }

        fun enviaNotificacao(funcionario: Funcionario) {
            val intent = Intent(this, FuncionarioActivity::class.java)
            intent.putExtra("disciplina", funcionario)
            NotificationUtil.create(1, intent, "BNPApp", "Você tem uma nova atividade, ${funcionario.nome}.")
        }
        fun taskFuncionarios() {
            Thread {

                funcionarios = FuncionarioService.getFuncionarios(context)
                runOnUiThread {
                    // Toast.makeText(context, "Clicou no funcionário ${funcionarios}", Toast.LENGTH_SHORT).show()

                    recyclerFuncionarios?.adapter = FuncionarioAdapter(funcionarios) { onClickFuncionario(it) }
                    enviaNotificacao(this.funcionarios[0])
                }
            }.start()
        }
        // tratamento do evento de clicar em uma disciplina
        fun onClickFuncionario(f: Funcionario) {
//            Toast.makeText(context, "Clicou no Funcionário: ${f.nome}", Toast.LENGTH_SHORT)
//                .show()


            Toast.makeText(context, "Clicou no funcionário ${f.nome}", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, FuncionarioActivity::class.java)
            intent.putExtra("disciplina", f)
            startActivityForResult(intent, REQUEST_REMOVE)
        }

    private fun configuraMenuLateral() {
        var toogle = ActionBarDrawerToggle(
            this,
            layoutMenuLateral,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_closed)
        layoutMenuLateral.addDrawerListener(toogle)
        toogle.syncState()
        menu_lateral.setNavigationItemSelectedListener(this)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CADASTRO || requestCode == REQUEST_REMOVE ) {
            // atualizar lista de disciplinas
            taskFuncionarios()
        }
    }
    fun showText(text: String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main, menu)
        // vincular evento de buscar
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView?)?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                showText(newText)
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
                    taskFuncionarios()
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
            var intent = Intent(this, NovoFuncionarioActivity::class.java)
            startActivityForResult(intent, REQUEST_CADASTRO)
            Toast.makeText(this, "Botão de Adicionar", Toast.LENGTH_LONG).show()
            }
        // botão up navigation
        else if (id == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}