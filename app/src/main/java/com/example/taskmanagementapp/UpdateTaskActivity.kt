package com.example.taskmanagementapp

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast


class UpdateTaskActivity(
    context: Context,
    private val task: Task,
    private val onUpdate: (Task) -> Unit
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_task)

        val etTaskName = findViewById<EditText>(R.id.etTaskName)
        val etTaskDescription = findViewById<EditText>(R.id.etTaskDescription)
        val etTaskPriority = findViewById<EditText>(R.id.etTaskPriority)
        val etTaskDeadline = findViewById<EditText>(R.id.etTaskDeadline)

        etTaskName.setText(task.name)
        etTaskDescription.setText(task.description)
        etTaskPriority.setText(task.priority.toString())
        etTaskDeadline.setText(task.deadline.toString())

        findViewById<View>(R.id.btnUpdate).setOnClickListener {

            val updatedName = etTaskName.text.toString()
            val updatedDescription = etTaskDescription.text.toString()
            val updatedPriority = etTaskPriority.text.toString().toIntOrNull()
            val updatedDeadline = etTaskDeadline.text.toString().toLongOrNull()

            if (updatedName.isEmpty() || updatedDescription.isEmpty() || updatedPriority == null || updatedDeadline == null) {
                Toast.makeText(context, "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val updatedTask = task.copy(
                name = updatedName,
                description = updatedDescription,
                priority = updatedPriority,
                deadline = updatedDeadline
            )

            onUpdate(updatedTask)

            dismiss()
        }
    }
}




