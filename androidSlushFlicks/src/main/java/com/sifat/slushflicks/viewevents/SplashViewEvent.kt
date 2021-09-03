package com.sifat.slushflicks.viewevents

sealed class SplashViewEvent : ViewEvent() {
    object UpdateGenreEvent : SplashViewEvent()
}
