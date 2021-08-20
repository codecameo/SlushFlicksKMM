package com.sifat.slushflicks.data.cache.column

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeColumn(
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
