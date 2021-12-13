package com.angee.developdroidmarket.room_db

import androidx.room.*

@Dao

interface ToDoDAO {
    @Query("SELECT * FROM ToDo")
    suspend fun getAllTask(): List<ToDo>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: ToDo): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: List<ToDo>?): List<Long>

    @Update
    suspend fun updateTask(task: ToDo)

    @Delete
    suspend fun deleteUsers(task: ToDo)
}