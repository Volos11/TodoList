package com.tetron.domain.usecase

import com.tetron.domain.repository.TaskRepository
import com.tetron.domain_models.Task

class GetAllTasksUseCase(
    private val repository: TaskRepository
) {
    suspend fun execute() : List<Task> =
        repository.getAllTasks()
}