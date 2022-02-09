package com.dikacandra.sebaran_kuliner_kota_pekanbaru.db

import androidx.room.*
import com.dikacandra.sebaran_kuliner_kota_pekanbaru.model.Restoran

@Dao
interface RestoranDao {

    @Insert
    fun insert(restoran: Restoran)

    @Update
    fun update(restoran: Restoran)

    @Delete
    fun delete(restoran: Restoran)

    @Query("SELECT * FROM restoran")
    fun getAll() : List<Restoran>

    @Query("SELECT * FROM restoran WHERE id = :id")
    fun getById(id: Int) : List<Restoran>
}