package com.example.eduardoapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FuncionarioDAO {
    @Query("SELECT * FROM funcionario where id = :id")
    fun getById(id:Long): Funcionario?
    @Query("SELECT * FROM funcionario")
    fun getAll(): List<Funcionario>
    @Insert
    fun insert(funcionario: Funcionario)
    @Delete
    fun delete(funcionario: Funcionario)
}