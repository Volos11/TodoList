package com.tetron.create_task.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tetron.create_task.interactor.CreateTaskInteractor
import com.tetron.domain_models.Tag
import com.tetron.domain_models.TaskShort
import kotlinx.coroutines.launch

class CreateTaskViewModel (
    private val interactor: CreateTaskInteractor
) : ViewModel() {
    private val defaultTag = Tag(name = "Без тега")
    private val _dataCurrentTag = MutableLiveData(defaultTag)
    private val _dataListOfTags = MutableLiveData<List<Tag>>()
    val dataListOfTags: LiveData<List<Tag>> = _dataListOfTags
    val dataCurrentTag: LiveData<Tag> = _dataCurrentTag

    private suspend fun updateDataListWithDefault() {
        val listTags = interactor.getAllTags().toMutableList()
        listTags.add(0, defaultTag)
        _dataListOfTags.value = listTags
    }

    fun getAllTags() = viewModelScope.launch {
        updateDataListWithDefault()
    }

    fun selectTagById(tag: Tag) {
        _dataCurrentTag.value = tag
    }

    fun createNewTask(task: TaskShort) = viewModelScope.launch {
        interactor.addNewTask(
            task, _dataCurrentTag.value?.id
        )
    }

    fun clear() {
        _dataCurrentTag.value = defaultTag
    }
}