package com.example.notes.fragments.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tetron.create_task.interactor.CreateNoteIntercector
import com.tetron.domain_models.NoteId
import com.tetron.domain_models.Tag
import kotlinx.coroutines.launch

class CreateNoteViewModel(
    private val interactor: CreateNoteIntercector

) : ViewModel() {
    private val defaultTag = Tag(name = "Без тега")
    private val _dataCurrentTag = MutableLiveData(defaultTag)


    fun createNote(task: NoteId) = viewModelScope.launch {
        interactor.addNewNote(task, _dataCurrentTag.value?.id)
    }

    fun changeNote(task: NoteId) = viewModelScope.launch {
        interactor.ChangeNote(task, _dataCurrentTag.value?.id)
    }


    suspend fun getNoteId(id: Long): NoteId {
        return interactor.getNoteId(id)
    }


}

class GetNoteViewModel(private val interactor: CreateNoteIntercector) : ViewModel() {
    private val _dataListOfNote = MutableLiveData<List<NoteId>>(emptyList())
    val dataListOfNote: LiveData<List<NoteId>> = _dataListOfNote

    private suspend fun updateData() {
        _dataListOfNote.value = interactor.GetAllNote()
    }

    fun deleteNote(id: Long) = viewModelScope.launch {

        interactor.DeleteNote(id)
    }

    fun GetallNote() = viewModelScope.launch {
        updateData()
    }

}