package com.example.eduardoapp

import java.io.Serializable

class Funcionario : Serializable {

    var id: Long = 0
    var nome = ""
    var cargo = ""
    var foto = ""


    override fun toString(): String {
        return "Funcionario(nome='$nome')"
    }
}
