package com.example.eduardoapp

import androidx.room.Room

object DatabaseManager {

    private var dbInstance: BNPDatabase


    init {
        val context = BNPApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
                context,
                BNPDatabase::class.java,
                "bnp.sqlite"
        ).build()
    }

    fun getFuncionarioDAO(): FuncionarioDAO {
        return dbInstance.funcionarioDAO()
    }

}