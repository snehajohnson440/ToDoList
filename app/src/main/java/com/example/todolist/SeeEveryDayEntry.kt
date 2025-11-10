package com.example.todolist

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.EverydayListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class SeeEveryDayEntry : AppCompatActivity() {
    private lateinit var binding: EverydayListBinding
    private lateinit var adapter : EveryDayList
    private val viewModel : ToDoListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EverydayListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = EveryDayList(
            {
                toDoList ->
                viewModel.updateEverydayList(toDoList.id)
                true
            }
            )
        binding.recyclerView.adapter = adapter
        viewModel.getEverydayList().observe(this){
            list ->
            adapter.submitList(list)
        }

    }
}