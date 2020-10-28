package com.example.eduardoapp

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.Response
import java.net.URL

object FuncionarioService {


    val host = "https://eduardovinicius.pythonanywhere.com"

    val TAG = "WS_BikeNaPorta"


//    fun getFuncionarios (context: Context): List<Funcionario> {
//        val funcionarios = mutableListOf<Funcionario>()
//// criar 10 disciplinas
//        for (i in 1..10) {
//            val d = Funcionario()
//            d.nome = "Disciplina $i"
//            d.ementa = "Ementa Disciplina $i"
//            d.professor = "Professor Disciplina $i"
//            d.foto = "https://dourasoft.com.br/site/dourasoft2017/wp-content/uploads/2016/02/Vendedor-de-Sucesso-copy.jpg"
//            funcionarios.add(d)
//        }
//        return funcionarios
//    }
//}



    fun getFuncionarios (context: Context): List<Funcionario> {
//
//        val url = "$host/funcionarios"
//        val json = HttpHelper.get(url)
//
//        Log.d(TAG, json)
//
//
//        return parserJson<List<Funcionario>>(json)


        if (AndroidUtils.isInternetDisponivel(context)){
            val url = "$host/funcionarios"
            val json = HttpHelper.get(url)

            Log.d(TAG, json)
            var f = parserJson<List<Funcionario>>(json)

            for (c in f) {
                saveOffline(c)
            }

            return f
        }
        else {
            val dao = DatabaseManager.getFuncionarioDAO()
            return dao.getAll()
        }
    }
    fun saveOffline (f: Funcionario) {
        val dao = DatabaseManager.getFuncionarioDAO()
        if (! existe(f)) {
            dao.insert(f)
        }
    }
    fun existe(f: Funcionario): Boolean {
        val dao = DatabaseManager.getFuncionarioDAO()
        return dao.getById(f.id) != null
    }


    fun save(f: Funcionario) {
        val url = "$host/funcionarios"
        if (HttpHelper.hasActiveInternetConnection()){
            val jsonFunc = GsonBuilder().create().toJson(f)

            HttpHelper.post(url, jsonFunc)
        }
        else {
            val dao = DatabaseManager.getFuncionarioDAO()
            dao.insert(f)
        }
//        val json = HttpHelper.post("$host/funcionarios", f.toJson())
//        return parserJson(json)

    }
    fun delete(f: Funcionario): Response {
        val url = "$host/funcionarios/${f.id}"
        val json = HttpHelper.delete(url)
        return parserJson<Response>(json)
    }
//
    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
}
}