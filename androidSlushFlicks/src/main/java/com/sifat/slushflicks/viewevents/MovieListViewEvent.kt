package com.sifat.slushflicks.viewevents

sealed class MovieListViewEvent : ViewEvent() {
    object FetchCollectionViewEvent : MovieListViewEvent()
}
