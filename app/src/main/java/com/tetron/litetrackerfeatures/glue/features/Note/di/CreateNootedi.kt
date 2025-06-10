package com.tetron.litetrackerfeatures.glue.features.Note.di




import com.tetron.create_task.interactor.CreateNoteIntercector
import com.tetron.create_task.interactor.CreateTaskInteractor
import com.tetron.litetrackerfeatures.glue.features.Note.interactor.CreateNoteInteractorImpl
import  com.example.notes.fragments.viewmodel.CreateNoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val CreateNote = module {
    factoryOf(::CreateNoteInteractorImpl) { bind<CreateNoteIntercector>() }
    viewModelOf(::CreateNoteViewModel)
}