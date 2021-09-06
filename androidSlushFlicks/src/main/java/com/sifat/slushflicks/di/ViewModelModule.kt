package com.sifat.slushflicks.di

import com.sifat.slushflicks.component.movie.MovieViewModel
import com.sifat.slushflicks.component.search.SearchViewModel
import com.sifat.slushflicks.component.splash.SplashViewModel
import com.sifat.slushflicks.component.tvshow.TvShowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun viewModelModule() = module {
    viewModel { SplashViewModel(genreUseCase = get(), appDispatchers = get()) }
    viewModel {
        MovieViewModel(
            collectionUseCase = get(),
            appDispatchers = get(),
            movieListUseCase = get(),
            trendingMovieListUseCase = get()
        )
    }
    viewModel {
        TvShowViewModel(
            collectionUseCase = get(),
            appDispatchers = get(),
            trendingTvListUseCase = get(),
            tvShowListUseCase = get()
        )
    }
    viewModel {
        SearchViewModel(
            movieSearchUseCase = get(),
            appDispatchers = get(),
            tvShowSearchUseCase = get()
        )
    }
}
