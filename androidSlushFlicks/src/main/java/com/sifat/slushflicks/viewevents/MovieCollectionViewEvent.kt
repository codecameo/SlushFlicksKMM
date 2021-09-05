package com.sifat.slushflicks.viewevents

sealed class MovieCollectionViewEvent : ViewEvent() {
    object FetchCollectionViewEvent : MovieCollectionViewEvent()
    class UpdateCollectionViewEvent(val label: String) : MovieCollectionViewEvent()
    object FetchMovieListViewEvent : MovieCollectionViewEvent()
    class LoadMoreMovieListViewEvent : MovieCollectionViewEvent()
}
