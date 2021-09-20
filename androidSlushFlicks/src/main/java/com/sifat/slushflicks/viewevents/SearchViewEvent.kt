package com.sifat.slushflicks.viewevents

import com.sifat.common.domain.utils.ShowType

sealed class SearchViewEvent : ViewEvent() {
    class QueryChangeViewEvent(val query: String) : SearchViewEvent()
    class LoadMoreShowViewEvent() : SearchViewEvent()
    class RefreshShowViewEvent() : SearchViewEvent()
    class UpdateShowTypeViewEvent(val showType: ShowType) : SearchViewEvent()
}
