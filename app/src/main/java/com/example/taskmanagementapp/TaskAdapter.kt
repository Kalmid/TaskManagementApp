package com.example.taskmanagementapp


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale


class TaskAdapter(
    private val onUpdate: (Task) -> Unit,
    private val onDelete: (Task) -> Unit
) : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskName: TextView = itemView.findViewById(R.id.tvTaskName)
        val taskDescription: TextView = itemView.findViewById(R.id.tvTaskDescription)
        val taskPriority: TextView = itemView.findViewById(R.id.tvTaskPriority)
        val taskDeadline: TextView = itemView.findViewById(R.id.tvTaskDeadline)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = getItem(position)

        holder.taskName.text = currentTask.name
        holder.taskDescription.text = currentTask.description
        holder.taskPriority.text = "Priority: ${currentTask.priority}"

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formattedDeadline = dateFormat.format(currentTask.deadline)
        holder.taskDeadline.text = "Deadline: $formattedDeadline"

        holder.itemView.setOnClickListener {
            onUpdate(currentTask)
        }

        holder.itemView.setOnLongClickListener {
            onDelete(currentTask)
            true
        }
    }
}

class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}


