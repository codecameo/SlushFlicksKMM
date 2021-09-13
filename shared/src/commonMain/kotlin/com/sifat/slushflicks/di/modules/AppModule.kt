package com.sifat.slushflicks.di.modules

import co.touchlab.kermit.Kermit
import com.sifat.slushflicks.data.broadcaster.Broadcaster
import com.sifat.slushflicks.data.broadcaster.impl.NetworkStateBroadcaster
import com.sifat.slushflicks.data.cache.ColumnTypeAdapter
import com.sifat.slushflicks.data.cache.DB_NAME
import com.sifat.slushflicks.data.cache.MovieEntity
import com.sifat.slushflicks.data.cache.SlushFlickDb
import com.sifat.slushflicks.data.cache.TvShowEntity
import com.sifat.slushflicks.data.getAppDispatcher
import com.sifat.slushflicks.data.manager.TimeManager
import com.sifat.slushflicks.data.manager.impl.TimeManagerImpl
import com.sifat.slushflicks.data.remote.API_KEY
import com.sifat.slushflicks.data.remote.DYNAMIC_LINK_BASE_URL
import com.sifat.slushflicks.data.remote.DYNAMIC_LINK_DOMAIN
import com.sifat.slushflicks.di.DiConstants.API_KEY_NAME
import com.sifat.slushflicks.di.DiConstants.DATABASE_NAME
import com.sifat.slushflicks.di.DiConstants.NAME_DYNAMIC_BASE_LINK
import com.sifat.slushflicks.di.DiConstants.NAME_DYNAMIC_DOMAIN
import com.sifat.slushflicks.di.getLogger
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun appModule() = module {
    single { Kermit(getLogger()) }
    single(named(name = API_KEY_NAME)) { API_KEY }
    single(named(name = DATABASE_NAME)) { DB_NAME }
    single(named(name = NAME_DYNAMIC_BASE_LINK)) { DYNAMIC_LINK_BASE_URL }
    single(named(name = NAME_DYNAMIC_DOMAIN)) { DYNAMIC_LINK_DOMAIN }
    single { ColumnTypeAdapter(get()) }
    single { getAppDispatcher() }
    single<Broadcaster<Boolean>> { NetworkStateBroadcaster(get()) }
    single<TimeManager> { TimeManagerImpl() }
    single {
        val adapter = get<ColumnTypeAdapter>()
        SlushFlickDb(
            get(),
            MovieEntityAdapter = MovieEntity.Adapter(
                genresAdapter = adapter.genreAdapter,
                castsAdapter = adapter.castAdapter
            ),
            TvShowEntityAdapter = TvShowEntity.Adapter(
                genresAdapter = adapter.genreAdapter,
                castsAdapter = adapter.castAdapter,
                seasonsAdapter = adapter.seasonAdapter,
                lastEpisodeAdapter = adapter.episodeAdapter,
                nextEpisodeAdapter = adapter.episodeAdapter
            )
        ).slushFlicksQueries
    }
}
