package com.tetron.domain.usecase

import com.tetron.domain.repository.TaskRepository

class DeleteTagUseCase(
    private val taskRepository: TaskRepository
) {
    suspend fun execute(id: Long) {
        taskRepository.deleteTagById(id)
    }
}