package com.tetron.litetrackerfeatures.glue.features.tag.di

import com.tetron.litetrackerfeatures.glue.features.tag.interactor.TagInteractorImpl
import com.tetron.tag.interactor.TagInteractor
import com.tetron.tag.viewmodel.TagViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val tagFeatureModule = module {
    factoryOf(::TagInteractorImpl) { bind<TagInteractor>() }
    viewModelOf(::TagViewModel)
}