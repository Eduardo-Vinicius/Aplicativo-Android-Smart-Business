package com.example.eduardoapp

import android.content.Context
import android.util.Log
import com.example.eduardoapp.HttpHelper.client
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.Request
import okhttp3.Response


object LoginService {
    val host = "https://eduardovinicius.pythonanywhere.com"
    val TAG = "ws_bnpApp"

    fun login(login: Login): Int? {
//        val json = HttpHelper.post("${host}/login", login.toJson())
//        return parserJson(json)

        val url = "${host}/login"
        val jsonLogin = GsonBuilder().create().toJson(login)
        Log.d("TESTE", jsonLogin)
        val status = HttpHelper.getStatus(url, jsonLogin)
        Log.d("teste", status.toString())
        return status
    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}