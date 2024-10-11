package com.example.taskmanagementapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.compose.ui.input.key.Key.Companion.R
import java.text.SimpleDateFormat
import java.util.Locale

class AddTaskActivity : ComponentActivity() {

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        val etTaskName = findViewById<EditText>(R.id.etTaskName)
        val etTaskDescription = findViewById<EditText>(R.id.etTaskDescription)
        val etTaskPriority = findViewById<EditText>(R.id.etTaskPriority)
        val etTaskDeadline = findViewById<EditText>(R.id.etTaskDeadline)
        val btnSaveTask = findViewById<Button>(R.id.btnSaveTask)

        btnSaveTask.setOnClickListener {
            val taskName = etTaskName.text.toString()
            val taskDescription = etTaskDescription.text.toString()
            val taskPriority = etTaskPriority.text.toString().toInt()
            val taskDeadlineStr = etTaskDeadline.text.toString()

            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = dateFormat.parse(taskDeadlineStr)

            if (date != null) {
                val taskDeadline = date.time

                val task = Task(
                    name = taskName,
                    description = taskDescription,
                    priority = taskPriority,
                    deadline = taskDeadline
                )

                taskViewModel.insert(task)

                finish()
            } else {
                Toast.makeText(
                    this,
                    "Invalid date format. Please use YYYY-MM-DD.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}