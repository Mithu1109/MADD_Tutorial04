package com.example.madd_tutorial04

class MyTableRepositories(private val db: MyDatabase) {
    suspend fun insert(myTable: MyTable) = db.getMyTableDao().insert(myTable)
    suspend fun update(myTable: MyTable) = db.getMyTableDao().update(myTable)
    suspend fun delete(myTable: MyTable) = db.getMyTableDao().delete(myTable)
    suspend fun getAll() = db.getMyTableDao().getAll()
    suspend fun getOne(name: String) = db.getMyTableDao().getOne(name)
}
