package com.tetron.litetrackerfeatures.glue.features.Note.di

import com.tetron.create_task.interactor.CreateNoteIntercector
import com.tetron.litetrackerfeatures.glue.features.Note.interactor.GetnoteIntercaptorImpl
import  com.example.notes.fragments.viewmodel.GetNoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val GetNoteDi = module {
    factoryOf(::GetnoteIntercaptorImpl){bind<CreateNoteIntercector>()}
    viewModelOf(::GetNoteViewModel)
}



