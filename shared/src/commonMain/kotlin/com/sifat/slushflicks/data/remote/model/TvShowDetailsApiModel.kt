package com.sifat.slushflicks.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvShowDetailsApiModel(
    @SerialName("id")
    val id: Long? = null,
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    @SerialName("created_by")
    val createdBy: List<CreatedBy>? = null,
    @SerialName("episode_run_time")
    val episodeRunTime: List<Int>? = null,
    @SerialName("first_air_date")
    val firstAirDate: String? = null,
    @SerialName("genres")
    val genres: List<GenreApiModel>? = null,
    @SerialName("last_episode_to_air")
    val lastEpisode: EpisodeApiModel? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("next_episode_to_air")
    val nextEpisode: EpisodeApiModel? = null,
    @SerialName("number_of_episodes")
    val episodeCount: Int? = null,
    @SerialName("number_of_seasons")
    val seasonCount: Int? = null,
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("overview")
    val overview: String? = null,
    @SerialName("popularity")
    val popularity: Double? = null,
    @SerialName("seasons")
    val seasons: List<SeasonApiModel>? = null,
    @SerialName("vote_average")
    val voteAverage: Double? = null,
    @SerialName("status")
    val status: String? = null,
    @SerialName("vote_count")
    val voteCount: Int? = null
)

@Serializable
data class CreatedBy(
    @SerialName("name")
    val name: String? = null
)

@Serializable
data class EpisodeApiModel(
    @SerialName("id")
    val id: Long? = null,
    @SerialName("still_path")
    val stillPath: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("episode_number")
    val episodeNumber: Int? = null,
    @SerialName("air_date")
    val airDate: String? = null,
    @SerialName("overview")
    val overview: String? = null,
    @SerialName("season_number")
    val seasonNumber: Int? = null,
    @SerialName("vote_average")
    val voteAvg: Double? = null
)

@Serializable
data class SeasonApiModel(
    @SerialName("id")
    val id: Long? = null,
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("episode_count")
    val episodeCount: Int? = null,
    @SerialName("air_date")
    val airDate: String? = null,
    @SerialName("overview")
    val overview: String? = null,
    @SerialName("season_number")
    val seasonNumber: Int? = null
)
