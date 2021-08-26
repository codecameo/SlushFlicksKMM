package com.sifat.slushflicks.di.modules

import com.sifat.slushflicks.domain.usecase.GenreUseCase
import com.sifat.slushflicks.domain.usecase.GetMovieListUseCase
import com.sifat.slushflicks.domain.usecase.GetTvShowListUseCase
import com.sifat.slushflicks.domain.usecase.MovieCollectionUseCase
import com.sifat.slushflicks.domain.usecase.MovieSearchUseCase
import com.sifat.slushflicks.domain.usecase.RecentMovieUseCase
import com.sifat.slushflicks.domain.usecase.RecentTvShowUseCase
import com.sifat.slushflicks.domain.usecase.TvCollectionUseCase
import com.sifat.slushflicks.domain.usecase.TvShowSearchUseCase
import com.sifat.slushflicks.domain.usecase.UpdateRecentMovieUseCase
import com.sifat.slushflicks.domain.usecase.UpdateRecentTvShowUseCase
import com.sifat.slushflicks.domain.usecase.impl.GenreUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.GetMovieListUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.GetTvShowListUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.MovieCollectionUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.MovieSearchUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.RecentMovieUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.RecentTvShowUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.TvCollectionUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.TvShowSearchUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.UpdateRecentMovieUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.UpdateRecentTvShowUseCaseImpl
import org.koin.dsl.module

fun useCaseModule() = module {
    factory<GenreUseCase> { GenreUseCaseImpl(get()) }
    factory<GetMovieListUseCase> { GetMovieListUseCaseImpl(get()) }
    factory<GetTvShowListUseCase> { GetTvShowListUseCaseImpl(get()) }
    factory<MovieCollectionUseCase> { MovieCollectionUseCaseImpl(get()) }
    factory<MovieSearchUseCase> { MovieSearchUseCaseImpl(get()) }
    factory<RecentMovieUseCase> { RecentMovieUseCaseImpl(get()) }
    factory<RecentTvShowUseCase> { RecentTvShowUseCaseImpl(get()) }
    factory<TvCollectionUseCase> { TvCollectionUseCaseImpl(get()) }
    factory<TvShowSearchUseCase> { TvShowSearchUseCaseImpl(get()) }
    factory<UpdateRecentMovieUseCase> { UpdateRecentMovieUseCaseImpl(get()) }
    factory<UpdateRecentTvShowUseCase> { UpdateRecentTvShowUseCaseImpl(get()) }
}
