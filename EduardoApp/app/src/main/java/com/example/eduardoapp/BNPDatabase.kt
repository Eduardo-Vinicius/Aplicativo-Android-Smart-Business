package com.example.eduardoapp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Funcionario:: class), version = 1)
abstract class BNPDatabase: RoomDatabase() {
    abstract fun funcionarioDAO(): FuncionarioDAO



}