package com.example.eduardoapp

import android.content.Context

object FuncionarioService {
        fun getFuncionarios (context: Context): List<Funcionario> {
            val funcionarios = mutableListOf<Funcionario>()
// criar 10 disciplinas
            for (i in 1..10) {
                val d = Funcionario()
                d.nome = "Funcionário $i"
                d.cargo = "Cargo $i"
                d.foto = "https://images.vexels.com/media/users/3/137047/isolated/preview/5831a17a290077c646a48c4db78a81bb---cone-de-perfil-de-usu--rio-azul-by-vexels.png"
                funcionarios.add(d)
            }
            return funcionarios
        }

}