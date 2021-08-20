package com.sifat.slushflicks.data.cache.column

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeasonColumn(
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
