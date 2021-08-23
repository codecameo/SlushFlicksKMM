package com.sifat.slushflicks.di.modules

import com.sifat.slushflicks.data.repository.GenreRepositoryImpl
import com.sifat.slushflicks.data.repository.SearchRepositoryImpl
import com.sifat.slushflicks.domain.repository.GenreRepository
import com.sifat.slushflicks.domain.repository.SearchRepository
import org.koin.dsl.module

fun repositoryModule() = module {
    factory<SearchRepository> { SearchRepositoryImpl(get(), get(), get()) }
    factory<GenreRepository> { GenreRepositoryImpl(get(), get(), get()) }
}
