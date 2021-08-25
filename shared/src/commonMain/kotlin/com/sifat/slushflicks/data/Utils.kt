package com.sifat.slushflicks.data

import com.sifat.slushflicks.data.Constants.DEFAULT_INT
import com.sifat.slushflicks.data.Constants.EMPTY_STRING
import com.sifat.slushflicks.data.cache.column.SeasonColumn
import com.sifat.slushflicks.data.mapper.toColumn
import com.sifat.slushflicks.data.remote.model.CreatedBy
import com.sifat.slushflicks.data.remote.model.SeasonApiModel
import com.sifat.slushflicks.data.remote.model.VideoApiModel

fun isYoutubeModel(videoApiModel: VideoApiModel): Boolean {
    return videoApiModel.run {
        site == YOUTUBE_SITE && type == VIDEO_TYPE_TRAILER
    }
}

fun getDirectors(createdBy: List<CreatedBy>?): String {
    return createdBy?.joinToString { " $BULLET_SIGN " } ?: EMPTY_STRING
}

fun getRuntime(runtimes: List<Int>?): Int {
    return if (runtimes.isNullOrEmpty()) DEFAULT_INT else runtimes.average().toInt()
}

fun getSeasons(models: List<SeasonApiModel>?): List<SeasonColumn> {
    return models?.filter { (it.seasonNumber ?: 0) > 0 }?.map { it.toColumn() } ?: emptyList()
}
