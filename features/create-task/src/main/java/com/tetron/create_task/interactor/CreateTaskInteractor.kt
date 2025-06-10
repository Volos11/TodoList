package com.tetron.create_task.interactor

import com.tetron.domain_models.Tag
import com.tetron.domain_models.TaskShort

interface CreateTaskInteractor {
    suspend fun getAllTags() : List<Tag>
    suspend fun addNewTask(task : TaskShort, tagId: Long?)
}