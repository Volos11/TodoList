package com.tetron.all_task.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tetron.all_task.interactor.AllTasksInteractor
import com.tetron.domain_models.Task
import kotlinx.coroutines.launch

class TaskViewModel(
    private val interactor: AllTasksInteractor
) : ViewModel() {
    private val _dataListOfTasks = MutableLiveData<List<Task>>(emptyList())
    val dataListOfTasks: LiveData<List<Task>> = _dataListOfTasks

    private suspend fun updateData()  {
        _dataListOfTasks.value = interactor.getAllTasks()
    }

    fun getAllTasks() = viewModelScope.launch {
        updateData()
    }

    fun deleteTask(taskId: Long) = viewModelScope.launch {
        interactor.deleteTaskById(taskId)
        updateData()
    }

    fun checkTask(taskId: Long, isCloseTask: Boolean) = viewModelScope.launch {
        interactor.checkTaskById(taskId, isCloseTask)
        updateData()
    }
}