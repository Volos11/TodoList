package com.tetron.domain.usecase

import com.tetron.domain.repository.TaskRepository
import com.tetron.domain_models.Tag

class GetAllTagsUseCase(
    private val taskRepository: TaskRepository
) {
    suspend fun execute(): List<Tag> =
        taskRepository.getAllTags()
}