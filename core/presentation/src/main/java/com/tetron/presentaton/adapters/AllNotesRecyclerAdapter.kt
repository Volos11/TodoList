package com.tetron.presentaton.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tetron.domain_models.NoteId
import com.tetron.domain_models.Task
import com.tetron.presentaton.R
import com.tetron.presentaton.databinding.ItemTaskBinding

class AllNotesRecyclerAdapter (
    private val onClickItem: (task: NoteId) -> Unit,
    private val onholdClickItem: (task: Long) -> Unit
)
    : RecyclerView.Adapter<AllNotesRecyclerAdapter.TaskHolder>() {
    class TaskHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)

    private lateinit var context: Context

    var listOfTasks: List<NoteId> = emptyList()
        set(value) {
            field = value
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
            title.text = task.name


            constraint.setOnClickListener {
                onClickItem(task)
            }
            constraint.setOnLongClickListener {
                onholdClickItem(task.id!!)
                notifyItemRemoved(position)
                notifyDataSetChanged()
                true
            }


            val color = if(check.isChecked) context.getColor(R.color.gray_white_2) else context.getColor(R.color.white)
            title.setTextColor(color)
            text.setTextColor(color)
            data.setTextColor(color)
            tagText.setTextColor(color)


        }
    }

    override fun getItemCount(): Int = listOfTasks.size
}