package com.example.eduardoapp

import android.provider.MediaStore
import android.util.Log
import com.example.eduardoapp.FuncionarioService.TAG
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.IOException

import okhttp3.*
import okhttp3.Response


object HttpHelper {
    val client = OkHttpClient()
    private val TAG = "HTTP_BNPApp"
    private val LOG_ON = true
    val JSON = MediaType.parse("application/json; charset=utf-8")

    fun get(url: String) : String {
        val request = Request.Builder().url(url).get().build()
        return getJson(request)
    }

    fun post(url: String, json: String): String {
        Log.d(TAG, "HttpHelper.post: $url > $json")
        val body = RequestBody.create(JSON, json)
        val request = Request.Builder().url(url).post(body).build()
        Log.d(TAG, body.toString())
        Log.d(TAG, request.toString())
        return getJson(request)
    }

    fun delete(url: String): String {
        Log.d(TAG, "HttpHelper.delete: $url")
        val request = Request.Builder().url(url).delete().build()
        return getJson(request)
    }
    fun getStatus(url: String, json: String): Int {
        try{
            val body = RequestBody.create(JSON, json)
            Log.d("body", body.toString())
            val request = Request.Builder().url(url).post(body).build()
            Log.d("request", request.toString())
            val httpResponse: Response = client.newCall(request).execute()
            Log.d("response", httpResponse.toString())


            val status = httpResponse.code();
            Log.d(TAG, status.toString())
            return status
        }catch (e: IOException) {
            e.printStackTrace();
        }
        return 0
    }



    fun getJson(request: Request?): String {
        val response = client.newCall(request).execute()
        val body = response.body()
        if (body != null) {
            val json = body.string()
            Log.d(TAG, "  << : $json")
            return json
        }
        throw IOException("Erro na requisição")
    }
    fun hasActiveInternetConnection() :Boolean{
        try{
            val request = Request.Builder().url("https://www.google.com").build()
            val httpResponse: Response = client.newCall(request).execute()
            val status = httpResponse.code();
            if (status == 200) {
                return true
            }
            return false
        }catch (e : IOException){
            e.printStackTrace()
        }
        return false
    }

}