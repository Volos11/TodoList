package com.tetron.domain.usecase

import com.tetron.domain.repository.TaskRepository
import com.tetron.domain_models.TaskShort

class AddNewTaskUseCase(private val repository: TaskRepository) {
    suspend fun execute(task: TaskShort, tagId: Long?) {
        repository.addNewTask(
            task.mapToFullTaskWithoutId(tagId)
        )
    }
}