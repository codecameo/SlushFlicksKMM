package com.sifat.slushflicks.viewevents

import com.sifat.slushflicks.component.search.ShowType

sealed class SearchViewEvent : ViewEvent() {
    class SearchShowViewEvent(val query: String) : SearchViewEvent()
    class LoadMoreShowViewEvent() : SearchViewEvent()
    class RefreshShowViewEvent() : SearchViewEvent()
    class UpdateShowTypeViewEvent(val showType: ShowType) : SearchViewEvent()
}
