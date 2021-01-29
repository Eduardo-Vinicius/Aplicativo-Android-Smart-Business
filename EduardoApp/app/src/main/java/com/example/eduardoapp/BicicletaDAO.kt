package com.example.eduardoapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BicicletaDAO {
    @Query("SELECT * FROM bicicleta where id = :id")
    fun getById(id:Long): Bicicleta?
    @Query("SELECT * FROM bicicleta")
    fun getAll(): List<Bicicleta>
    @Insert
    fun insert(bicicleta: Bicicleta)
    @Delete
    fun delete(bicicleta: Bicicleta)
}