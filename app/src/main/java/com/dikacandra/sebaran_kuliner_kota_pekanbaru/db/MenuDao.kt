package com.dikacandra.sebaran_kuliner_kota_pekanbaru.db

import androidx.room.*
import com.dikacandra.sebaran_kuliner_kota_pekanbaru.model.Menu

@Dao
interface MenuDao {

    @Insert
    fun insert(menu: Menu)

    @Update
    fun update(menu: Menu)

    @Delete
    fun delete(menu: Menu)

    @Query("SELECT * FROM menu")
    fun getAll() : List<Menu>

    @Query("SELECT * FROM menu WHERE id = :id")
    fun getById(id: Int) : List<Menu>
}