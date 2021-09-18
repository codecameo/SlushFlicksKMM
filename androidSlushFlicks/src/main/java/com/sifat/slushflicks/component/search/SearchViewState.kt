package com.sifat.slushflicks.component.search

import com.sifat.slushflicks.data.Constants.EMPTY_STRING
import com.sifat.slushflicks.data.Constants.INVALID_INT
import com.sifat.slushflicks.domain.model.ShowModel
import com.sifat.slushflicks.domain.utils.ShowType
import com.sifat.slushflicks.domain.utils.ShowType.MOVIE

class SearchViewState {
    var searchResult = mutableListOf<ShowModel>()
    var query = EMPTY_STRING
    var showType = MOVIE
    var page = 0
    var isLoading = false

    fun updateQuery(query: String) {
        this.query = query.trim()
        page = 0
        isLoading = false
    }

    fun updateShowType(showType: ShowType) {
        this.showType = showType
        page = 0
        isLoading = false
    }

    fun addShowList(result: List<ShowModel>) {
        if (page == 0) searchResult.clear()
        if (result.isNullOrEmpty()) {
            page = INVALID_INT
            return
        }
        searchResult.addAll(result)
        page++
    }
}
