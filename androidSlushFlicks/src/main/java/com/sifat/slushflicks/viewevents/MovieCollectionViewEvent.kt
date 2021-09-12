package com.sifat.slushflicks.viewevents

sealed class MovieCollectionViewEvent : ViewEvent() {
    class FetchCollectionViewEvent : MovieCollectionViewEvent()
    class UpdateCollectionViewEvent(val label: String) : MovieCollectionViewEvent()
    class FetchMovieListViewEvent : MovieCollectionViewEvent()
    class LoadMoreMovieListViewEvent : MovieCollectionViewEvent()
}
