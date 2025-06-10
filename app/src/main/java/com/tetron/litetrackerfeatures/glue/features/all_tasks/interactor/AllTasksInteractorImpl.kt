package com.tetron.litetrackerfeatures.glue.features.all_tasks.interactor

import com.tetron.all_task.interactor.AllTasksInteractor
import com.tetron.domain.usecase.CheckTaskUseCase
import com.tetron.domain.usecase.DeleteTagUseCase
import com.tetron.domain.usecase.DeleteTaskUseCase
import com.tetron.domain.usecase.GetAllTasksUseCase
import com.tetron.domain_models.Task

class AllTasksInteractorImpl(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val checkTaskUseCase: CheckTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : AllTasksInteractor {
    override suspend fun getAllTasks(): List<Task> =
        getAllTasksUseCase.execute()

    override suspend fun checkTaskById(idTask: Long, checkStatus: Boolean) =
        checkTaskUseCase.execute(idTask, checkStatus)

    override suspend fun deleteTaskById(idTask: Long) =
        deleteTaskUseCase.execute(idTask)
}