package com.sifat.slushflicks.viewevents

import android.net.Uri

sealed class HomeViewEvent : ViewEvent() {
    class FetchDynamicLinkViewEvent(val uri: Uri) : HomeViewEvent()
}
