package com.sifat.slushflicks.domain.model

import com.sifat.slushflicks.data.Constants.DEFAULT_DOUBLE
import com.sifat.slushflicks.data.Constants.DEFAULT_INT
import com.sifat.slushflicks.data.Constants.EMPTY_STRING
import com.sifat.slushflicks.data.Constants.INVALID_ID_LONG

data class EpisodeModel(
    val id: Long = INVALID_ID_LONG,
    val stillPath: String = EMPTY_STRING,
    val name: String = EMPTY_STRING,
    val episodeNumber: Int = DEFAULT_INT,
    val airDate: String = EMPTY_STRING,
    val overview: String = EMPTY_STRING,
    val seasonNumber: Int = DEFAULT_INT,
    val voteAvg: Double = DEFAULT_DOUBLE
)
