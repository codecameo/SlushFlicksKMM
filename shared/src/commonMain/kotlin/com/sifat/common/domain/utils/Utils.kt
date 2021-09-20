package com.sifat.common.domain.utils

import com.sifat.common.data.remote.IMAGE_BASE_URL
import com.sifat.common.data.remote.ImageDimension.W154
import com.sifat.common.data.remote.ImageDimension.W185
import com.sifat.common.data.remote.ImageDimension.W500
import com.sifat.common.data.remote.ImageDimension.W780
import com.sifat.common.data.remote.ImageDimension.W92

fun getBackdropImage(path: String) = getFullImagePath(W500.dimension, path)

fun getPosterImage(path: String) = getFullImagePath(W780.dimension, path)

fun getCastImage(path: String) = getFullImagePath(W185.dimension, path)

fun getEpisodeImage(path: String) = getFullImagePath(W154.dimension, path)

fun getSessionImage(path: String) = getFullImagePath(W92.dimension, path)

fun getFullImagePath(dimension: String, path: String) = "$IMAGE_BASE_URL$dimension$path"
