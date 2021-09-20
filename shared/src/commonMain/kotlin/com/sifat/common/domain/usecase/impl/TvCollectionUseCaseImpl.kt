package com.sifat.common.domain.usecase.impl

import com.sifat.common.data.state.DataState
import com.sifat.common.domain.mapper.toModel
import com.sifat.common.domain.model.CollectionModel
import com.sifat.common.domain.repository.TvHomeRepository
import com.sifat.common.domain.usecase.TvCollectionUseCase

class TvCollectionUseCaseImpl(
    private val tvHomeRepository: TvHomeRepository
) : BaseUseCase(), TvCollectionUseCase {
    override suspend fun execute(): DataState<List<CollectionModel>> {
        return getDataState(tvHomeRepository.getTvCollection()) { list ->
            list?.map { it.toModel() }
        }
    }
}
