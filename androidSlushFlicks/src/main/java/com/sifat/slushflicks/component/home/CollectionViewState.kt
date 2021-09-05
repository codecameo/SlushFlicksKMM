package com.sifat.slushflicks.component.home

import com.sifat.slushflicks.component.home.model.CollectionListModel
import com.sifat.slushflicks.data.Constants.EMPTY_STRING
import com.sifat.slushflicks.data.Constants.INVALID_INT
import com.sifat.slushflicks.domain.model.CollectionModel
import com.sifat.slushflicks.domain.model.ShowModel

class CollectionViewState {
    var collectionItems = emptyList<CollectionListModel>()
    var currentSelectedLabel: String = EMPTY_STRING
    var page: Int = 0
    var isLoadingMore: Boolean = false
    val showList: MutableList<ShowModel> = mutableListOf()

    fun initCollectionList(list: List<CollectionModel>?) {
        collectionItems = list?.mapIndexed { index, collectionModel ->
            CollectionListModel(
                name = collectionModel.name,
                label = collectionModel.label,
                selected = index == 0
            )
        } ?: emptyList()
        reset(collectionItems.firstOrNull()?.label ?: EMPTY_STRING)
    }

    fun updateSelectedLabel(label: String) {
        reset(label = label)
        collectionItems = collectionItems.map {
            CollectionListModel(
                name = it.name,
                label = it.label,
                selected = it.label == label
            )
        }
    }

    fun addShowList(shows: List<ShowModel>) {
        if (shows.isNullOrEmpty()) {
            page = INVALID_INT
            return
        }
        if (page == 0) showList.clear()
        showList.addAll(shows)
        page++
    }

    private fun reset(label: String = EMPTY_STRING) {
        page = 0
        isLoadingMore = false
        showList.clear()
        currentSelectedLabel = label
    }
}
