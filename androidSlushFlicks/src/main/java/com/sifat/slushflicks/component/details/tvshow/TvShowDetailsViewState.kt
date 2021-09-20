package com.sifat.slushflicks.component.details.tvshow

import com.sifat.common.data.Constants.INVALID_ID_LONG
import com.sifat.common.domain.model.TvShowModel

class TvShowDetailsViewState {
    var tvShowId: Long = INVALID_ID_LONG
    var tvShowModel: TvShowModel? = null
    var reviewPage: Int = 0
}
