package com.tetron.domain.usecase

import com.tetron.domain.repository.TaskRepository

class CheckTaskUseCase(
    private val repository: TaskRepository
) {
    suspend fun execute(idTask: Long, checkStatus: Boolean = true) {
        repository.updateStatusTaskById(idTask, checkStatus)
    }
}