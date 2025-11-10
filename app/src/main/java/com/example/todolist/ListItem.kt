package com.example.todolist

import android.icu.text.SimpleDateFormat
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.Locale

@Entity(tableName = "listitem")
data class ListItem(
    @PrimaryKey(autoGenerate = true) val id : Long = 0,
    val content : String,
    val time : Long?,
    val everyDayList : Boolean = false,
    val onTodayList : Boolean = true,
    val marked : Boolean = false,
    val date: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
)