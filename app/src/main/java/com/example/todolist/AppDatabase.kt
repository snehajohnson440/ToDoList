package com.example.todolist

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ListItem::class], version = 2, exportSchema = true)
abstract class AppDatabase : RoomDatabase()
{
    abstract fun dao() : ToDoListDao
}