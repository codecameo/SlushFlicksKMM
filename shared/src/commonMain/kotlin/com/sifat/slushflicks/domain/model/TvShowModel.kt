package com.sifat.slushflicks.domain.model

data class TvShowModel(
    val id: Long,
    val voteCount: Int,
    val voteAvg: Double,
    val title: String,
    val releaseData: String,
    val backdropPath: String,
    val overview: String,
    val posterPath: String,
    val status: String,
    val video: String,
    val popularity: Double,
    val runtime: Int,
    val directors: String,
    val numOfEpisode: Int,
    val numOfSeason: Int,
    val genres: List<GenreModel>,
    val casts: List<CastModel>,
    val nextEpisode: EpisodeModel?,
    val lastEpisode: EpisodeModel?,
    val seasons: List<SeasonModel>?
)
