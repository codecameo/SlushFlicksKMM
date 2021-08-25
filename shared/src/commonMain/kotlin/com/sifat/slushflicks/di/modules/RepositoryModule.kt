package com.sifat.slushflicks.di.modules

import com.sifat.slushflicks.data.repository.GenreRepositoryImpl
import com.sifat.slushflicks.data.repository.MovieDetailsRepositoryImpl
import com.sifat.slushflicks.data.repository.MovieHomeRepositoryImpl
import com.sifat.slushflicks.data.repository.MovieListRepositoryImpl
import com.sifat.slushflicks.data.repository.SearchRepositoryImpl
import com.sifat.slushflicks.data.repository.TrendingMovieListRepositoryImpl
import com.sifat.slushflicks.data.repository.TrendingTvListRepositoryImpl
import com.sifat.slushflicks.data.repository.TvDetailsRepositoryImpl
import com.sifat.slushflicks.data.repository.TvHomeRepositoryImpl
import com.sifat.slushflicks.data.repository.TvListRepositoryImpl
import com.sifat.slushflicks.di.DiConstants.MOVIE_LIST_OTHER_NAME
import com.sifat.slushflicks.di.DiConstants.MOVIE_LIST_TRENDING_NAME
import com.sifat.slushflicks.di.DiConstants.TV_SHOW_LIST_OTHER_NAME
import com.sifat.slushflicks.di.DiConstants.TV_SHOW_LIST_TRENDING_NAME
import com.sifat.slushflicks.domain.repository.GenreRepository
import com.sifat.slushflicks.domain.repository.MovieDetailsRepository
import com.sifat.slushflicks.domain.repository.MovieHomeRepository
import com.sifat.slushflicks.domain.repository.MovieListRepository
import com.sifat.slushflicks.domain.repository.SearchRepository
import com.sifat.slushflicks.domain.repository.TvDetailsRepository
import com.sifat.slushflicks.domain.repository.TvHomeRepository
import com.sifat.slushflicks.domain.repository.TvListRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun repositoryModule() = module {
    factory<SearchRepository> { SearchRepositoryImpl(get(), get(), get()) }
    factory<GenreRepository> { GenreRepositoryImpl(get(), get(), get()) }
    factory<MovieHomeRepository> { MovieHomeRepositoryImpl(get(), get()) }
    factory<TvHomeRepository> { TvHomeRepositoryImpl(get(), get()) }
    factory<MovieListRepository>(named(name = MOVIE_LIST_OTHER_NAME)) {
        MovieListRepositoryImpl(
            get(),
            get(),
            get()
        )
    }
    factory<TvListRepository>(named(name = TV_SHOW_LIST_OTHER_NAME)) {
        TvListRepositoryImpl(
            get(),
            get(),
            get()
        )
    }
    factory<MovieListRepository>(named(name = MOVIE_LIST_TRENDING_NAME)) {
        TrendingMovieListRepositoryImpl(
            get(),
            get(),
            get()
        )
    }
    factory<TvListRepository>(named(name = TV_SHOW_LIST_TRENDING_NAME)) {
        TrendingTvListRepositoryImpl(
            get(),
            get(),
            get()
        )
    }
    factory<MovieDetailsRepository> { MovieDetailsRepositoryImpl(get(), get(), get(), get()) }
    factory<TvDetailsRepository> { TvDetailsRepositoryImpl(get(), get(), get(), get()) }
}
