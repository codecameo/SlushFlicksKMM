package com.sifat.slushflicks.component.movie

import com.sifat.slushflicks.component.movie.model.CollectionListModel
import com.sifat.slushflicks.domain.model.CollectionModel

class MovieViewState {
    var collectionItems = emptyList<CollectionListModel>()

    fun initCollectionList(list: List<CollectionModel>?) {
        collectionItems = list?.mapIndexed { index, collectionModel ->
            CollectionListModel(
                name = collectionModel.name,
                label = collectionModel.label,
                selected = index == 0
            )
        } ?: emptyList()
    }

    fun updateSelectedLabel(label: String) {
        collectionItems = collectionItems.map {
            CollectionListModel(
                name = it.name,
                label = it.label,
                selected = it.label == label
            )
        }
    }
}
