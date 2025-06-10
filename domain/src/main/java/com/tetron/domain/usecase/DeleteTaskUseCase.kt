package com.tetron.domain.usecase

import com.tetron.domain.repository.TaskRepository

class DeleteTaskUseCase(
    private val repository: TaskRepository
) {
    suspend fun execute(taskId: Long) {
        repository.deleteTaskById(taskId)
    }
}