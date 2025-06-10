package com.tetron.tag.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tetron.domain_models.Tag
import com.tetron.domain_models.TagWithoutId
import com.tetron.tag.interactor.TagInteractor
import kotlinx.coroutines.launch

class TagViewModel(
    private val interactor: TagInteractor
) : ViewModel() {
    private val _data = MutableLiveData<List<Tag>>()
    val data: LiveData<List<Tag>> = _data

    private suspend fun updateData() {
        _data.value = interactor.getAllTags()
    }

    fun getAllTags() = viewModelScope.launch {
        updateData()
    }

    fun addNewTag(tag: TagWithoutId) = viewModelScope.launch {
        interactor.addNewTag(tag)
        updateData()
    }

    fun deleteTagById(id: Long) = viewModelScope.launch {
        interactor.deleteTag(id)
        updateData()
    }
}