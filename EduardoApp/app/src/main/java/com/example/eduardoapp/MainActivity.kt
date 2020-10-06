package com.example.eduardoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.os.Handler
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.login_constraint.*

class MainActivity : DebugActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_constraint)

        //lblLogin.setText(R.string.msg_login)
        //lblSenha.setText(R.string.msg_senha)

        button.setOnClickListener{
            val user = txtNewLogin.text.toString()
            val pass = txtNewSenha.text.toString()
            if (user == "aluno" && pass == "impacta"){
            progress_login.visibility = View.VISIBLE
            Handler().postDelayed(
                {
                    progress_login.visibility = View.GONE

                    Toast.makeText(this, "Olá, $user", Toast.LENGTH_LONG).show()

                    var intent = Intent(this, TelaInicialActivity::class.java)

                    //            var params = Bundle()
                    //            params.putString("nome_usuario", user)
                    //            params.putInt("numero", 10)
                    //
                    //            intent.putExtras(params)

                    intent.putExtra("nome_usuario", user)
                    intent.putExtra("numero", 10)

                    startActivityForResult(intent, 0)
                }, 3000
            )
         }
            else
            {
               Toast.makeText(this, "Usuário e/ou senha incorretos!", Toast.LENGTH_LONG).show()
            }

        }

//        textoUm.setText(R.string.mensagem_inicial)
//        campoImagem.setImageResource(R.drawable.imagem_login)

    }
}