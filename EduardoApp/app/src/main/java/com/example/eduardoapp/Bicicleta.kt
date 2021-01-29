package com.example.eduardoapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable
@Entity(tableName="bicicleta")
class Bicicleta : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id:Long = 0
    var nome = ""
    var cor = ""
    var foto = ""



    override fun toString(): String {
        return "Bicicleta(nome='$nome')"
    }
    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}
