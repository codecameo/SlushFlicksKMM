package com.sifat.slushflicks.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieApiModel(
    @SerialName("id")
    val id: Long? = null,
    @SerialName("vote_count")
    val voteCount: Int? = null,
    @SerialName("vote_average")
    val voteAverage: Double? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("release_date")
    val releaseDate: String? = null,
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    @SerialName("overview")
    val overview: String? = null,
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("popularity")
    val popularity: Double? = null,
    @SerialName("genre_ids")
    val genreIds: List<Long>? = null
)
