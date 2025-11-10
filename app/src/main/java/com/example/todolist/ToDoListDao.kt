package com.example.todolist

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ToDoListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodayList(listItem: ListItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEverydayList(listItem: ListItem)

    @Query("SELECT * FROM listitem WHERE everyDayList = 1")
    fun getEveryDayList() : LiveData<List<ListItem>>

    @Query("UPDATE listitem SET everyDayList = 0 WHERE id = :id")
    suspend fun updateEveryDayList(id : Long)

    @Query("UPDATE listitem SET marked = CASE WHEN marked = 1 THEN 0 ELSE 1 END WHERE id = :id")
    suspend fun updateMark(id : Long)

    @Query("SELECT * FROM listitem WHERE everyDayList = 1 OR onTodayList = 1 AND date = :date ORDER BY marked ASC")
    fun getTodayList(date : String) : LiveData<List<ListItem>>

    @Query("UPDATE listitem SET onTodayList = 0 WHERE id = :id")
    suspend fun removeFromTodayList(id : Long)

}