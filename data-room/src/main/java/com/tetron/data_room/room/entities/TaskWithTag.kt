package com.tetron.data_room.room.entities

import com.tetron.domain_models.Task

data class TaskWithTag(
    val taskId: Long,
    val taskTitle: String,
    val taskDescription: String,
    val taskDateMill: Long,
    val taskStatus: Boolean,
    val taskIdTag: Long? = null,
    val tagName: String? = null
) {
    fun mapToTask() = Task(
        taskId,
        taskTitle,
        taskDescription,
        taskDateMill,
        taskStatus,
        taskIdTag,
        tagName
    )
}
