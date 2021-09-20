package com.sifat.common.di.modules

import com.sifat.common.di.DiConstants.MOVIE_LIST_OTHER_NAME
import com.sifat.common.di.DiConstants.MOVIE_LIST_TRENDING_NAME
import com.sifat.common.di.DiConstants.TV_SHOW_LIST_OTHER_NAME
import com.sifat.common.di.DiConstants.TV_SHOW_LIST_TRENDING_NAME
import com.sifat.common.domain.usecase.GenerateDynamicLinkUseCase
import com.sifat.common.domain.usecase.GenreUseCase
import com.sifat.common.domain.usecase.GetDynamicLinkUseCase
import com.sifat.common.domain.usecase.GetMovieListUseCase
import com.sifat.common.domain.usecase.GetMovieReviewUseCase
import com.sifat.common.domain.usecase.GetTrendingMovieListUseCase
import com.sifat.common.domain.usecase.GetTrendingTvListUseCase
import com.sifat.common.domain.usecase.GetTvReviewUseCase
import com.sifat.common.domain.usecase.GetTvShowListUseCase
import com.sifat.common.domain.usecase.MovieCollectionUseCase
import com.sifat.common.domain.usecase.MovieDetailsUseCase
import com.sifat.common.domain.usecase.MovieSearchUseCase
import com.sifat.common.domain.usecase.RecentMovieUseCase
import com.sifat.common.domain.usecase.RecentTvShowUseCase
import com.sifat.common.domain.usecase.RecommendedMovieUseCase
import com.sifat.common.domain.usecase.RecommendedTvShowUseCase
import com.sifat.common.domain.usecase.SimilarMovieUseCase
import com.sifat.common.domain.usecase.SimilarTvShowUseCase
import com.sifat.common.domain.usecase.TvCollectionUseCase
import com.sifat.common.domain.usecase.TvShowDetailsUseCase
import com.sifat.common.domain.usecase.TvShowSearchUseCase
import com.sifat.common.domain.usecase.UpdateRecentMovieUseCase
import com.sifat.common.domain.usecase.UpdateRecentTvShowUseCase
import com.sifat.common.domain.usecase.impl.GenerateDynamicLinkUseCaseImpl
import com.sifat.common.domain.usecase.impl.GenreUseCaseImpl
import com.sifat.common.domain.usecase.impl.GetDynamicLinkUseCaseImpl
import com.sifat.common.domain.usecase.impl.GetMovieListUseCaseImpl
import com.sifat.common.domain.usecase.impl.GetMovieReviewUseCaseImpl
import com.sifat.common.domain.usecase.impl.GetTrendingMovieListUseCaseImpl
import com.sifat.common.domain.usecase.impl.GetTrendingTvListUseCaseImpl
import com.sifat.common.domain.usecase.impl.GetTvReviewUseCaseImpl
import com.sifat.common.domain.usecase.impl.GetTvShowListUseCaseImpl
import com.sifat.common.domain.usecase.impl.MovieCollectionUseCaseImpl
import com.sifat.common.domain.usecase.impl.MovieDetailsUseCaseImpl
import com.sifat.common.domain.usecase.impl.MovieSearchUseCaseImpl
import com.sifat.common.domain.usecase.impl.RecentMovieUseCaseImpl
import com.sifat.common.domain.usecase.impl.RecentTvShowUseCaseImpl
import com.sifat.common.domain.usecase.impl.RecommendedMovieUseCaseImpl
import com.sifat.common.domain.usecase.impl.RecommendedTvShowUseCaseImpl
import com.sifat.common.domain.usecase.impl.SimilarMovieUseCaseImpl
import com.sifat.common.domain.usecase.impl.SimilarTvShowUseCaseImpl
import com.sifat.common.domain.usecase.impl.TvCollectionUseCaseImpl
import com.sifat.common.domain.usecase.impl.TvShowDetailsUseCaseImpl
import com.sifat.common.domain.usecase.impl.TvShowSearchUseCaseImpl
import com.sifat.common.domain.usecase.impl.UpdateRecentMovieUseCaseImpl
import com.sifat.common.domain.usecase.impl.UpdateRecentTvShowUseCaseImpl
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
    factory<GenerateDynamicLinkUseCase> { GenerateDynamicLinkUseCaseImpl(get()) }
    factory<GetDynamicLinkUseCase> { GetDynamicLinkUseCaseImpl(get()) }
}
