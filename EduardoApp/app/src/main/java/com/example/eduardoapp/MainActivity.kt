package com.example.eduardoapp

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.os.Handler
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.widget.Toast
import kotlinx.android.synthetic.main.adapter_funcionario.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.login_constraint.*

class MainActivity : DebugActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_constraint)

        Prefs.setString("nome", "Novo nome na string")
        Prefs.setBoolean("professor", true)

        txtNewLogin.setText(Prefs.getString("lembrarNome"))
        txtNewSenha.setText(Prefs.getString("lembrarSenha"))
        checkBoxLembrar.isChecked = Prefs.getBoolean("lembrar")
        //lblLogin.setText(R.string.msg_login)
        //lblSenha.setText(R.string.msg_senha)

        button.setOnClickListener{
            val user = txtNewLogin.text.toString()
            val pass = txtNewSenha.text.toString()

            val lembrar = checkBoxLembrar.isChecked
            Prefs.setBoolean("lembrar", lembrar)
            if (lembrar) {
                Prefs.setString("lembrarNome", user)
                Prefs.setString("lembrarSenha", pass)
            } else {
                Prefs.setString("lembrarNome", "")
                Prefs.setString("lembrarSenha", "")
            }

            val login = Login()
            login.login = user
            login.senha = pass

            Thread {
                val status = LoginService.login(login)

                Log.d("Login", status.toString())
                runOnUiThread {
                    if (status == 200) {

                        var intent = Intent(this, TelaInicialActivity::class.java)
                        intent.putExtra("nome_usuario", user)
                        //cardProgress.visibility = View.GONE
                        startActivity(intent)
                    }
                    if (status == 0) {
                        Toast.makeText(
                            this,
                            "Você precisa estar conectado à internet para logar",
                            Toast.LENGTH_LONG
                        ).show()
                       // cardProgress.visibility = GONE
                    } else {
                        Toast.makeText(this, "Usuário ou senha incorretos", Toast.LENGTH_LONG)
                            .show()
                        //cardProgress.visibility = View.GONE
                    }

                }
            }.start()

        }

//
//            if (user == "aluno" && pass == "impacta"){
//            progress_login.visibility = View.VISIBLE
//            Handler().postDelayed(
//                {
//                    progress_login.visibility = View.GONE
//
//                    Toast.makeText(this, "Olá, $user", Toast.LENGTH_LONG).show()
//
//                    var intent = Intent(this, TelaInicialActivity::class.java)
//
//                    //            var params = Bundle()
//                    //            params.putString("nome_usuario", user)
//                    //            params.putInt("numero", 10)
//                    //
//                    //            intent.putExtras(params)
//
//                    intent.putExtra("nome_usuario", user)
//                    intent.putExtra("numero", 10)
//
//                    startActivityForResult(intent, 0)
//                }, 3000
//            )
//         }
//            else
//            {
//               Toast.makeText(this, "Usuário e/ou senha incorretos!", Toast.LENGTH_LONG).show()
//            }
//
//        }

//        textoUm.setText(R.string.mensagem_inicial)
//        campoImagem.setImageResource(R.drawable.imagem_login)

    }
}