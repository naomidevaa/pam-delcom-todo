package com.ifs21021.delcomtodo.data.local.room

import com.ifs21021.delcomtodo.data.local.entity.DelcomTodoEntity
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DelcomTodoEntity::class], version = 1, exportSchema = false)
abstract class DelcomTodoDatabase : RoomDatabase() {
  abstract fun delcomTodoDao(): IDelcomTodoDao

  companion object {
    private const val Database_NAME = "DelcomTodo.db"

    @Volatile
    private var INSTANCE: DelcomTodoDatabase? = null

    @JvmStatic
    fun getInstance(context: Context): DelcomTodoDatabase {
      if (INSTANCE == null) {
        synchronized(DelcomTodoDatabase::class.java) {
          INSTANCE = Room.databaseBuilder(
            context.applicationContext,
            DelcomTodoDatabase::class.java,
            Database_NAME
          ).build()
        }
      }
      return INSTANCE as DelcomTodoDatabase
    }
  }
}