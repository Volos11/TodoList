package com.tetron.presentaton.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tetron.domain_models.Task
import com.tetron.presentaton.R
import com.tetron.presentaton.adapters.diffutil.TaskDiffUtilCallback
import com.tetron.presentaton.databinding.ItemTaskBinding
import java.text.DateFormat
import java.util.Date

class AllTaskRecyclerAdapter (
    private val onClickItem: (taskItem: Task) -> Unit,
    private val onLongClickItem: (taskItem: Task) -> Unit,
    private val onClickCheck: (taskId: Long, checkStatus: Boolean) -> Unit
) : RecyclerView.Adapter<AllTaskRecyclerAdapter.TaskHolder>() {
    class TaskHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)

    private lateinit var context: Context

    var listOfTasks: List<Task> = emptyList()
        set(value) {
            val callback = TaskDiffUtilCallback(
                oldList = field,
                newList = value
            )
            field = value
            DiffUtil
                .calculateDiff(callback)
                .dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val inflater = LayoutInflater.from(parent.context)
        context = parent.context
        val binding = ItemTaskBinding.inflate(inflater, parent, false)
        return TaskHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        val task = listOfTasks[position]


        with(holder.binding) {
            title.text = task.title
            text.text = task.description
            tagText.text = task.tagName ?: "Без тега"
            data.text = DateFormat.getDateInstance().format(Date(task.dateMill))
            check.isChecked = task.status

            val color = if(check.isChecked) context.getColor(R.color.gray_white_2) else context.getColor(R.color.white)
            title.setTextColor(color)
            text.setTextColor(color)
            data.setTextColor(color)
            tagText.setTextColor(color)

            check.setOnClickListener {
                onClickCheck(task.id, check.isChecked)
            }

            constraint.setOnClickListener {
                onClickItem(task)
            }

            constraint.setOnLongClickListener {
                onLongClickItem(task)
                true
            }
        }
    }

    override fun getItemCount(): Int = listOfTasks.size
}