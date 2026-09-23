package com.example.madd_tutorial04

import androidx.room.*

@Dao
interface MyTableDao {
    @Insert
    suspend fun insert(myTable: MyTable)

    @Update
    suspend fun update(myTable: MyTable)

    @Delete
    suspend fun delete(myTable: MyTable)

    @Query("SELECT * FROM MyTable")
    suspend fun getAll(): List<MyTable>

    @Query("SELECT * FROM MyTable WHERE name = :name")
    suspend fun getOne(name: String): MyTable
}
