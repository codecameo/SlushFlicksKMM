package com.sifat.slushflicks.viewevents

sealed class TvCollectionViewEvent : ViewEvent() {
    object FetchCollectionViewEvent : TvCollectionViewEvent()
}
