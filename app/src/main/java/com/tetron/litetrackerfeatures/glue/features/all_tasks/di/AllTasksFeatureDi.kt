package com.tetron.litetrackerfeatures.glue.features.all_tasks.di

import com.tetron.all_task.interactor.AllTasksInteractor
import com.tetron.all_task.viewmodel.TaskViewModel
import com.tetron.litetrackerfeatures.glue.features.all_tasks.interactor.AllTasksInteractorImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val allTasksFeatureModule = module {
    factoryOf(::AllTasksInteractorImpl) { bind<AllTasksInteractor>() }
    viewModelOf(::TaskViewModel)
}