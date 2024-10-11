package com.example.taskmanagementapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val priority: Int,
    val deadline: Long // Or String, depending on your data type
)





