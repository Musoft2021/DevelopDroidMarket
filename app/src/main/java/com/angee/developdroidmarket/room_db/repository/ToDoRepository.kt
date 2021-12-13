package com.angee.developdroidmarket.room_db.repository

import com.angee.developdroidmarket.room_db.ToDo
import com.angee.developdroidmarket.room_db.ToDoDAO


class ToDoRepository (private val toDoDAO: ToDoDAO) {
    suspend fun getAllTask(): List<ToDo>{
        return toDoDAO.getAllTask()
    }
    suspend fun insertTask(toDo: ToDo): Long {
        return toDoDAO.insertTask(toDo)
    }

    suspend fun  insertTask(toDo: List<ToDo>?): List<Long> {
      return toDoDAO.insertTask(toDo)
    }

}

