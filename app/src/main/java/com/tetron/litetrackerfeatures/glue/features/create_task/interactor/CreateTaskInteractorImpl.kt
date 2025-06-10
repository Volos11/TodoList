package com.tetron.litetrackerfeatures.glue.features.create_task.interactor

import com.tetron.create_task.interactor.CreateTaskInteractor
import com.tetron.domain.usecase.AddNewTaskUseCase
import com.tetron.domain.usecase.GetAllTagsUseCase
import com.tetron.domain_models.Tag
import com.tetron.domain_models.TaskShort

class CreateTaskInteractorImpl(
    private val getAllTagsUseCase: GetAllTagsUseCase,
    private val addNewTaskUseCase: AddNewTaskUseCase
) : CreateTaskInteractor {
    override suspend fun getAllTags(): List<Tag> =
        getAllTagsUseCase.execute()

    override suspend fun addNewTask(task: TaskShort, tagId: Long?) =
        addNewTaskUseCase.execute(task, tagId)
}