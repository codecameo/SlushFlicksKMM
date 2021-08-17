package com.sifat.slushflicks.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsApiModel(
    @SerialName("id")
    val id: Long? = null,
    @SerialName("video")
    val video: Boolean? = null,
    @SerialName("vote_count")
    val voteCount: Int? = null,
    @SerialName("vote_average")
    val voteAverage: Double? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("release_date")
    val releaseDate: String? = null,
    @SerialName("original_language")
    val originalLanguage: String? = null,
    @SerialName("original_title")
    val originalTitle: String? = null,
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    @SerialName("adult")
    val adult: Boolean? = null,
    @SerialName("overview")
    val overview: String? = null,
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("popularity")
    val popularity: Double? = null,
    @SerialName("genres")
    val genres: List<GenreApiModel>? = null,
    @SerialName("status")
    val status: String? = null,
    @SerialName("tagline")
    val tagline: String? = null,
    @SerialName("revenue")
    val revenue: Long? = null,
    @SerialName("budget")
    val budget: Long? = null,
    @SerialName("runtime")
    val runtime: Int? = null
)
