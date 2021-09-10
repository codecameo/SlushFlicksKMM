package com.sifat.slushflicks.domain.model

import com.sifat.slushflicks.data.Constants.DEFAULT_DOUBLE
import com.sifat.slushflicks.data.Constants.DEFAULT_INT
import com.sifat.slushflicks.data.Constants.EMPTY_STRING
import com.sifat.slushflicks.data.Constants.INVALID_ID_LONG

data class TvShowModel(
    val id: Long = INVALID_ID_LONG,
    val voteCount: Int = DEFAULT_INT,
    val voteAvg: Double = DEFAULT_DOUBLE,
    val title: String = EMPTY_STRING,
    val releaseData: String = EMPTY_STRING,
    val backdropPath: String = EMPTY_STRING,
    val overview: String = EMPTY_STRING,
    val posterPath: String = EMPTY_STRING,
    val status: String = EMPTY_STRING,
    val video: String = EMPTY_STRING,
    val popularity: Double = DEFAULT_DOUBLE,
    val runtime: Int = DEFAULT_INT,
    val directors: String = EMPTY_STRING,
    val numOfEpisode: Int = DEFAULT_INT,
    val numOfSeason: Int = DEFAULT_INT,
    val genres: List<GenreModel> = emptyList(),
    val casts: List<CastModel> = emptyList(),
    val nextEpisode: EpisodeModel? = null,
    val lastEpisode: EpisodeModel? = null,
    val seasons: List<SeasonModel>? = null
)
