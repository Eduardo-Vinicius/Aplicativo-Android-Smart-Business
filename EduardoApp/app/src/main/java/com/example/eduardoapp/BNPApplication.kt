package com.example.eduardoapp

import android.app.Application
import java.lang.IllegalStateException

class BNPApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: BNPApplication? = null

        fun getInstance(): BNPApplication {
            if (appInstance == null) {
                throw IllegalStateException (
                    "Configurar application no AndroidManifest"
                )
            }
            return appInstance!!
        }

    }
    // chamado quando android terminar processo da aplicação
    override fun onTerminate() {
        super.onTerminate()
    }
}