package com.sifat.slushflicks.di.modules

import com.sifat.slushflicks.di.DiConstants.MOVIE_LIST_OTHER_NAME
import com.sifat.slushflicks.di.DiConstants.MOVIE_LIST_TRENDING_NAME
import com.sifat.slushflicks.di.DiConstants.TV_SHOW_LIST_OTHER_NAME
import com.sifat.slushflicks.di.DiConstants.TV_SHOW_LIST_TRENDING_NAME
import com.sifat.slushflicks.domain.usecase.GenreUseCase
import com.sifat.slushflicks.domain.usecase.GetDynamicLinkUseCase
import com.sifat.slushflicks.domain.usecase.GetMovieListUseCase
import com.sifat.slushflicks.domain.usecase.GetMovieReviewUseCase
import com.sifat.slushflicks.domain.usecase.GetTrendingMovieListUseCase
import com.sifat.slushflicks.domain.usecase.GetTrendingTvListUseCase
import com.sifat.slushflicks.domain.usecase.GetTvReviewUseCase
import com.sifat.slushflicks.domain.usecase.GetTvShowListUseCase
import com.sifat.slushflicks.domain.usecase.MovieCollectionUseCase
import com.sifat.slushflicks.domain.usecase.MovieDetailsUseCase
import com.sifat.slushflicks.domain.usecase.MovieSearchUseCase
import com.sifat.slushflicks.domain.usecase.RecentMovieUseCase
import com.sifat.slushflicks.domain.usecase.RecentTvShowUseCase
import com.sifat.slushflicks.domain.usecase.RecommendedMovieUseCase
import com.sifat.slushflicks.domain.usecase.RecommendedTvShowUseCase
import com.sifat.slushflicks.domain.usecase.SimilarMovieUseCase
import com.sifat.slushflicks.domain.usecase.SimilarTvShowUseCase
import com.sifat.slushflicks.domain.usecase.TvCollectionUseCase
import com.sifat.slushflicks.domain.usecase.TvShowDetailsUseCase
import com.sifat.slushflicks.domain.usecase.TvShowSearchUseCase
import com.sifat.slushflicks.domain.usecase.UpdateRecentMovieUseCase
import com.sifat.slushflicks.domain.usecase.UpdateRecentTvShowUseCase
import com.sifat.slushflicks.domain.usecase.impl.GenreUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.GetDynamicLinkUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.GetMovieListUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.GetMovieReviewUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.GetTrendingMovieListUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.GetTrendingTvListUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.GetTvReviewUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.GetTvShowListUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.MovieCollectionUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.MovieDetailsUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.MovieSearchUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.RecentMovieUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.RecentTvShowUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.RecommendedMovieUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.RecommendedTvShowUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.SimilarMovieUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.SimilarTvShowUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.TvCollectionUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.TvShowDetailsUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.TvShowSearchUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.UpdateRecentMovieUseCaseImpl
import com.sifat.slushflicks.domain.usecase.impl.UpdateRecentTvShowUseCaseImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun useCaseModule() = module {
    factory<GenreUseCase> { GenreUseCaseImpl(get()) }
    factory<GetMovieListUseCase> { GetMovieListUseCaseImpl(get(named(name = MOVIE_LIST_OTHER_NAME))) }
    factory<GetTvShowListUseCase> { GetTvShowListUseCaseImpl(get(named(name = TV_SHOW_LIST_OTHER_NAME))) }
    factory<GetTrendingMovieListUseCase> { GetTrendingMovieListUseCaseImpl(get(named(name = MOVIE_LIST_TRENDING_NAME))) }
    factory<GetTrendingTvListUseCase> { GetTrendingTvListUseCaseImpl(get(named(name = TV_SHOW_LIST_TRENDING_NAME))) }
    factory<MovieCollectionUseCase> { MovieCollectionUseCaseImpl(get()) }
    factory<MovieSearchUseCase> { MovieSearchUseCaseImpl(get()) }
    factory<RecentMovieUseCase> { RecentMovieUseCaseImpl(get()) }
    factory<RecentTvShowUseCase> { RecentTvShowUseCaseImpl(get()) }
    factory<TvCollectionUseCase> { TvCollectionUseCaseImpl(get()) }
    factory<TvShowSearchUseCase> { TvShowSearchUseCaseImpl(get()) }
    factory<UpdateRecentMovieUseCase> { UpdateRecentMovieUseCaseImpl(get()) }
    factory<UpdateRecentTvShowUseCase> { UpdateRecentTvShowUseCaseImpl(get()) }
    factory<SimilarMovieUseCase> { SimilarMovieUseCaseImpl(get()) }
    factory<SimilarTvShowUseCase> { SimilarTvShowUseCaseImpl(get()) }
    factory<RecommendedTvShowUseCase> { RecommendedTvShowUseCaseImpl(get()) }
    factory<RecommendedMovieUseCase> { RecommendedMovieUseCaseImpl(get()) }
    factory<GetTvReviewUseCase> { GetTvReviewUseCaseImpl(get()) }
    factory<GetMovieReviewUseCase> { GetMovieReviewUseCaseImpl(get()) }
    factory<MovieDetailsUseCase> { MovieDetailsUseCaseImpl(get(), get()) }
    factory<TvShowDetailsUseCase> { TvShowDetailsUseCaseImpl(get(), get()) }
    factory<GetDynamicLinkUseCase> { GetDynamicLinkUseCaseImpl(get()) }
}
