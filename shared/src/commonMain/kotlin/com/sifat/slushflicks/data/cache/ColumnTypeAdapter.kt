package com.sifat.slushflicks.data.cache

import com.sifat.slushflicks.data.cache.column.CastColumn
import com.sifat.slushflicks.data.cache.column.EpisodeColumn
import com.sifat.slushflicks.data.cache.column.GenreColumn
import com.sifat.slushflicks.data.cache.column.SeasonColumn
import com.squareup.sqldelight.ColumnAdapter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ColumnTypeAdapter(private val json: Json) {
    val genreAdapter = object : ColumnAdapter<List<GenreColumn>, String> {
        override fun decode(databaseValue: String): List<GenreColumn> {
            return json.decodeFromString(databaseValue)
        }

        override fun encode(value: List<GenreColumn>): String {
            return json.encodeToString(value)
        }
    }

    val castAdapter = object : ColumnAdapter<List<CastColumn>, String> {
        override fun decode(databaseValue: String): List<CastColumn> {
            return json.decodeFromString(databaseValue)
        }

        override fun encode(value: List<CastColumn>): String {
            return json.encodeToString(value)
        }
    }

    val seasonAdapter = object : ColumnAdapter<List<SeasonColumn>, String> {
        override fun decode(databaseValue: String): List<SeasonColumn> {
            return json.decodeFromString(databaseValue)
        }

        override fun encode(value: List<SeasonColumn>): String {
            return json.encodeToString(value)
        }
    }

    val episodeAdapter = object : ColumnAdapter<EpisodeColumn, String> {
        override fun decode(databaseValue: String): EpisodeColumn {
            return json.decodeFromString(databaseValue)
        }

        override fun encode(value: EpisodeColumn): String {
            return json.encodeToString(value)
        }
    }
}
