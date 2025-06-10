package com.tetron.litetrackerfeatures.glue.features.create_task.di


import com.tetron.create_task.interactor.CreateTaskInteractor
import com.tetron.create_task.viewmodel.CreateTaskViewModel
import com.tetron.litetrackerfeatures.glue.features.create_task.interactor.CreateTaskInteractorImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val createTaskFeatureModule = module {
    factoryOf(::CreateTaskInteractorImpl) { bind<CreateTaskInteractor>() }
    viewModelOf(::CreateTaskViewModel)
}