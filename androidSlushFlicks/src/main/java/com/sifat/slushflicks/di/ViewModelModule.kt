package com.sifat.slushflicks.di

import com.sifat.slushflicks.component.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun viewModelModule() = module {
    viewModel { SplashViewModel(genreUseCase = get(), appDispatchers = get()) }
}
