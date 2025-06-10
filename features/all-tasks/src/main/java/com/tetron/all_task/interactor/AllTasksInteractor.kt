package com.tetron.all_task.interactor

import com.tetron.domain_models.Task

interface AllTasksInteractor {
    suspend fun getAllTasks() : List<Task>
    suspend fun checkTaskById(idTask: Long, checkStatus: Boolean = true)
    suspend fun deleteTaskById(idTask: Long)
}