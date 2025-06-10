package com.tetron.data_room.room.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.tetron.domain_models.Task
import com.tetron.domain_models.TaskWithoutId

@Entity(tableName = "task_table",
    foreignKeys = [
        ForeignKey(entity = TagDbEntity::class,
        parentColumns = ["id"],
        childColumns = ["tagId"],
        onDelete = ForeignKey.SET_NULL)
    ]
)
data class TaskDbEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String,
    val dateMill: Long,
    val status: Boolean,
    val tagId: Long?
) {
    fun mapToTask(): Task = Task(id, title, description, dateMill, status, tagId)

    companion object {
        fun mapToDbEntity(task: TaskWithoutId): TaskDbEntity =
            TaskDbEntity(
                title = task.title,
                description = task.description,
                dateMill = task.dateMill,
                status = task.status,
                tagId = task.tagId
            )
    }
}
