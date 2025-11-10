package com.example.todolist

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.databinding.AddDailyEntryBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class AddDailyEntry : AppCompatActivity() {

    private lateinit var binding: AddDailyEntryBinding
    private val viewModel: ToDoListViewModel by viewModels()

    private var selectedTimeMillis: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddDailyEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // pick time
        binding.time.setOnClickListener {
            showTimePicker { timeInMillis, formattedTime ->
                selectedTimeMillis = timeInMillis
                binding.time.text = formattedTime
            }
        }

        // insert when user clicks save
        binding.saveDailyEntry.setOnClickListener {
            val note = binding.note.text.toString().trim()
            if (note.isNotEmpty()) {
                val listItem = ListItem(
                    content = note,
                    time = selectedTimeMillis
                )
                viewModel.insertTodayList(listItem)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

    private fun showTimePicker(onTimeSelected: (Long, String) -> Unit) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                val selectedCalendar = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, selectedHour)
                    set(Calendar.MINUTE, selectedMinute)
                    set(Calendar.SECOND, 0)
                    set(Calendar.MILLISECOND, 0)
                }
                val timeInMillis = selectedCalendar.timeInMillis
                val formattedTime = formatTime(selectedHour, selectedMinute)
                onTimeSelected(timeInMillis, formattedTime)
            },
            hour,
            minute,
            false
        ).show()
    }

    private fun formatTime(hour: Int, minute: Int): String {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
        }
        val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return formatter.format(calendar.time)
    }
}
