package com.sifat.common.data

import com.sifat.common.data.Constants.DEFAULT_INT
import com.sifat.common.data.Constants.EMPTY_STRING
import com.sifat.common.data.cache.column.SeasonColumn
import com.sifat.common.data.mapper.toColumn
import com.sifat.common.data.remote.model.CreatedBy
import com.sifat.common.data.remote.model.SeasonApiModel
import com.sifat.common.data.remote.model.VideoApiModel

fun isYoutubeModel(videoApiModel: VideoApiModel): Boolean {
    return videoApiModel.run {
        site == YOUTUBE_SITE && type == VIDEO_TYPE_TRAILER
    }
}

fun getDirectors(createdBy: List<CreatedBy>?): String {
    return createdBy?.joinToString(separator = " $BULLET_SIGN ") { it.name ?: EMPTY_STRING }
        ?: EMPTY_STRING
}

fun getRuntime(runtimes: List<Int>?): Int {
    return if (runtimes.isNullOrEmpty()) DEFAULT_INT else runtimes.average().toInt()
}

fun getSeasons(models: List<SeasonApiModel>?): List<SeasonColumn> {
    return models?.filter { (it.seasonNumber ?: 0) > 0 }?.map { it.toColumn() } ?: emptyList()
}
