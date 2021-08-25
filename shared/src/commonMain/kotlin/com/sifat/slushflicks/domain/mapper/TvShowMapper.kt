package com.sifat.slushflicks.domain.mapper

/*
fun getEpisode(model: Episode?): EpisodeApiModel? {
    return model?.run {
        EpisodeModel(
            id = id,
            airDate = airDate ?: Constants.EMPTY_STRING,
            name = name,
            stillPath = stillPath ?: Constants.EMPTY_STRING,
            overview = overview,
            voteAvg = voteAvg,
            seasonNumber = seasonNumber,
            episodeNumber = episodeNumber
        )
    }
}

fun getSeasons(models: List<Season>?): List<SeasonApiModel> {
    return models?.let { seasons ->
        val seasonModels = mutableListOf<SeasonApiModel>()
        for (season in seasons) {
            if (season.seasonNumber != 0) seasonModels.add(getSeason(season))
        }
        seasonModels
    } ?: emptyList()
}

fun getSeason(season: Season): SeasonApiModel {
    return season.run {
        SeasonModel(
            id = id,
            airDate = airDate ?: Constants.EMPTY_STRING,
            name = name,
            posterPath = posterPath ?: Constants.EMPTY_STRING,
            overview = overview,
            seasonNumber = seasonNumber,
            episodeCount = episodeCount
        )
    }
}*/
