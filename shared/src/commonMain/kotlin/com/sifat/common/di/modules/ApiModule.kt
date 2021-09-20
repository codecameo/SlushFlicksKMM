package com.sifat.common.di.modules

import com.sifat.common.data.remote.api.GenreApi
import com.sifat.common.data.remote.api.MovieApi
import com.sifat.common.data.remote.api.SearchApi
import com.sifat.common.data.remote.api.TvShowApi
import com.sifat.common.data.remote.api.impl.GenreApiImpl
import com.sifat.common.data.remote.api.impl.MovieApiImpl
import com.sifat.common.data.remote.api.impl.SearchApiImpl
import com.sifat.common.data.remote.api.impl.TvShowApiImpl
import com.sifat.common.di.DiConstants.API_KEY_NAME
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun apiModule() = module {
    single<SearchApi> {
        SearchApiImpl(
            client = get(),
            baseUrlBuilder = get(),
            apiKey = get(named(name = API_KEY_NAME)),
            apiErrorParser = get()
        )
    }
    single<TvShowApi> {
        TvShowApiImpl(
            client = get(),
            baseUrlBuilder = get(),
            apiKey = get(named(name = API_KEY_NAME)),
            apiErrorParser = get()
        )
    }
    single<MovieApi> {
        MovieApiImpl(
            client = get(),
            baseUrlBuilder = get(),
            apiKey = get(named(name = API_KEY_NAME)),
            apiErrorParser = get()
        )
    }
    single<GenreApi> {
        GenreApiImpl(
            client = get(),
            baseUrlBuilder = get(),
            apiKey = get(named(name = API_KEY_NAME)),
            apiErrorParser = get()
        )
    }
}
