package com.example.eduardoapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable
@Entity(tableName="funcionario")
class Funcionario : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id:Long = 0
    var nome = ""
    var cargo = ""
    var foto = ""



    override fun toString(): String {
        return "Funcionario(nome='$nome')"
    }
    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}
