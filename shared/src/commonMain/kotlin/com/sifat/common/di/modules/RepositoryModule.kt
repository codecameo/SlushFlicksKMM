package com.sifat.common.di.modules

import com.sifat.common.data.repository.GenreRepositoryImpl
import com.sifat.common.data.repository.MovieDetailsRepositoryImpl
import com.sifat.common.data.repository.MovieHomeRepositoryImpl
import com.sifat.common.data.repository.MovieListRepositoryImpl
import com.sifat.common.data.repository.RecentRepositoryImpl
import com.sifat.common.data.repository.SearchRepositoryImpl
import com.sifat.common.data.repository.TrendingMovieListRepositoryImpl
import com.sifat.common.data.repository.TrendingTvListRepositoryImpl
import com.sifat.common.data.repository.TvDetailsRepositoryImpl
import com.sifat.common.data.repository.TvHomeRepositoryImpl
import com.sifat.common.data.repository.TvListRepositoryImpl
import com.sifat.common.di.DiConstants.MOVIE_LIST_OTHER_NAME
import com.sifat.common.di.DiConstants.MOVIE_LIST_TRENDING_NAME
import com.sifat.common.di.DiConstants.TV_SHOW_LIST_OTHER_NAME
import com.sifat.common.di.DiConstants.TV_SHOW_LIST_TRENDING_NAME
import com.sifat.common.domain.repository.GenreRepository
import com.sifat.common.domain.repository.MovieDetailsRepository
import com.sifat.common.domain.repository.MovieHomeRepository
import com.sifat.common.domain.repository.MovieListRepository
import com.sifat.common.domain.repository.RecentRepository
import com.sifat.common.domain.repository.SearchRepository
import com.sifat.common.domain.repository.TvDetailsRepository
import com.sifat.common.domain.repository.TvHomeRepository
import com.sifat.common.domain.repository.TvListRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun repositoryModule() = module {
    factory<SearchRepository> { SearchRepositoryImpl(get(), get(), get()) }
    factory<GenreRepository> { GenreRepositoryImpl(get(), get(), get()) }
    factory<MovieHomeRepository> { MovieHomeRepositoryImpl(get(), get()) }
    factory<TvHomeRepository> { TvHomeRepositoryImpl(get(), get()) }
    factory<MovieListRepository>(named(name = MOVIE_LIST_OTHER_NAME)) {
        MovieListRepositoryImpl(get(), get(), get())
    }
    factory<TvListRepository>(named(name = TV_SHOW_LIST_OTHER_NAME)) {
        TvListRepositoryImpl(get(), get(), get())
    }
    factory<MovieListRepository>(named(name = MOVIE_LIST_TRENDING_NAME)) {
        TrendingMovieListRepositoryImpl(get(), get(), get())
    }
    factory<TvListRepository>(named(name = TV_SHOW_LIST_TRENDING_NAME)) {
        TrendingTvListRepositoryImpl(get(), get(), get())
    }
    factory<MovieDetailsRepository> { MovieDetailsRepositoryImpl(get(), get(), get()) }
    factory<TvDetailsRepository> { TvDetailsRepositoryImpl(get(), get(), get()) }
    factory<RecentRepository> { RecentRepositoryImpl(get(), get()) }
}
