package com.example.todolist

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ToDoListRepository @Inject constructor(private val dao: ToDoListDao) {

    suspend fun insertTodayItem(listItem: ListItem) = dao.insertTodayList(listItem)

    suspend fun insertEverydayItem(listItem: ListItem) = dao.insertEverydayList(listItem)

    fun getEverydayList() = dao.getEveryDayList()

    suspend fun updateEverydayList(id : Long){
        dao.updateEveryDayList(id)
    }

    val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    fun getTodayList() = dao.getTodayList(today)

    suspend fun removeFromTodayList(id : Long){
        dao.removeFromTodayList(id)
    }

    suspend fun markItem(id : Long){
        dao.updateMark(id)
    }
}