package com.example.taskmanagementapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : ComponentActivity() {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        taskAdapter = TaskAdapter()
        recyclerView.adapter = taskAdapter


        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]


        taskViewModel.allTasks.observe(this, Observer { tasks ->
            tasks?.let {
                taskAdapter.submitList(it)
            }
        })


        val fabAddTask = findViewById<FloatingActionButton>(R.id.fabAddTask)
        fabAddTask.setOnClickListener {

            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }
    }
}
