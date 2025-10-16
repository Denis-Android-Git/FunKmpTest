package com.example.testpartnerkin.di

import com.example.testpartnerkin.data.RepositoryImpl
import com.example.testpartnerkin.domain.`interface`.Repository
import com.example.testpartnerkin.presentation.viewModels.DetailScreenViewModel
import com.example.testpartnerkin.presentation.viewModels.MainScreenViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module


expect val platformModule: Module

val sharedModule = module {
    singleOf(::RepositoryImpl).bind<Repository>()
    viewModelOf(::MainScreenViewModel)
    viewModelOf(::DetailScreenViewModel)
}