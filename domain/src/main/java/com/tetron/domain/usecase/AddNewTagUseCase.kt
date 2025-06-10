package com.tetron.domain.usecase

import com.tetron.domain.repository.TaskRepository
import com.tetron.domain_models.TagWithoutId

class AddNewTagUseCase(
    private val taskRepository: TaskRepository
) {
    suspend fun execute(mewTag: TagWithoutId) {
        taskRepository.addNewTag(mewTag)
    }
}