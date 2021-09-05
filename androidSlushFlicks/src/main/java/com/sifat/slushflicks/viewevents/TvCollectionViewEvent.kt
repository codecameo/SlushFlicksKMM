package com.sifat.slushflicks.viewevents

sealed class TvCollectionViewEvent : ViewEvent() {
    object FetchCollectionViewEvent : TvCollectionViewEvent()
    class UpdateCollectionViewEvent(val label: String) : MovieCollectionViewEvent()
    object FetchTvShowListViewEvent : MovieCollectionViewEvent()
    class LoadMoreTvShowListViewEvent : MovieCollectionViewEvent()
}
