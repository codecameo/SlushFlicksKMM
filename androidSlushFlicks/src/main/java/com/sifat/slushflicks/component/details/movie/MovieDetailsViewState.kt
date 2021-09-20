package com.sifat.slushflicks.component.details.movie

import com.sifat.common.data.Constants.INVALID_ID_LONG
import com.sifat.common.domain.model.MovieModel

class MovieDetailsViewState {
    var movieId: Long = INVALID_ID_LONG
    var movieModel: MovieModel? = null
    var reviewPage = 0
}
