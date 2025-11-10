package com.example.todolist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ToDoListViewModel  @Inject constructor(private val toDoListRepository: ToDoListRepository): ViewModel(){

    fun insertTodayList(listItem: ListItem){
       viewModelScope.launch {
            toDoListRepository.insertTodayItem(listItem)
       }
    }

    fun insertEverydayItem(listItem: ListItem){
        viewModelScope.launch {
            val updatedItem = listItem.copy(
                everyDayList = true,
                onTodayList = true
            )
            toDoListRepository.insertEverydayItem(updatedItem)
        }
    }

    fun getEverydayList() = toDoListRepository.getEverydayList()

    fun updateEverydayList(id : Long){
        viewModelScope.launch {
            toDoListRepository.updateEverydayList(id)
        }
    }

    fun getTodayList() = toDoListRepository.getTodayList()

    fun removeFromTodayList(id : Long){
        viewModelScope.launch {
            toDoListRepository.removeFromTodayList(id)
        }
    }
    fun markItem(id: Long){
        viewModelScope.launch {
            toDoListRepository.markItem(id)
        }
    }



}