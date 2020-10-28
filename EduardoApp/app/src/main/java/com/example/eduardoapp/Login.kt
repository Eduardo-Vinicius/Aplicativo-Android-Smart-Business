package com.example.eduardoapp

import com.google.gson.GsonBuilder
import java.io.Serializable

class Login : Serializable{
    var login = ""
    var senha = ""


fun toJson(): String {
    return GsonBuilder().create().toJson(this)

}
}