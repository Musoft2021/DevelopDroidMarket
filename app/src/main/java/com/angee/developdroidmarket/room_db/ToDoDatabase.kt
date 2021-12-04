package com.angee.developdroidmarket.room_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = arrayOf(ToDo::class), version=1)
abstract class ToDoDatabase: RoomDatabase(){
    abstract fun todoDAO(): ToDoDAO

    companion object {
        @Volatile
        private var INSTANCE: ToDoDatabase?=null

        fun getDatabase(context: Context) : ToDoDatabase {
            return  INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                ToDoDatabase::class.java,
                    "ToDoDatabase"
                    ).build()
                INSTANCE = instance
                instance
            }
        }

    }
}
