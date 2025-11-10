package com.example.todolist

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ToDoListAdapter

    private val viewModel : ToDoListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.date.text = java.text.SimpleDateFormat("EEE dd MMM", java.util.Locale.getDefault()).format(java.util.Date())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ToDoListAdapter({
            listItem ->
            viewModel.removeFromTodayList(listItem.id)
            true
        },
            {
                listItem ->
                viewModel.markItem(listItem.id)

            })

        binding.recyclerView.adapter = adapter
        viewModel.getTodayList().observe(this){
                list ->
            adapter.submitList(list)
        }

        binding.addEntry.setOnClickListener {
            dialogSelect()
        }

    }
    private fun dialogSelect() {
        val options = arrayOf("Add Today Entry", "Add Everyday Entry", "View All Entries")

        AlertDialog.Builder(this)
            .setTitle("Select")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> {
                        startActivity(Intent(this,AddDailyEntry::class.java))
                        finish()
                    }
                    1 -> {
                        startActivity(Intent(this,AddEveryDayEntry::class.java))
                        finish()
                    }
                    2 -> {
                        startActivity(Intent(this,SeeEveryDayEntry::class.java))
                        finish()
                    }
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}