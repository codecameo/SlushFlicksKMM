package com.sifat.slushflicks.domain.usecase.impl

import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.mapper.toModel
import com.sifat.slushflicks.domain.model.CollectionModel
import com.sifat.slushflicks.domain.repository.TvHomeRepository
import com.sifat.slushflicks.domain.usecase.TvCollectionUseCase

class TvCollectionUseCaseImpl(
    private val tvHomeRepository: TvHomeRepository
) : BaseUseCase(), TvCollectionUseCase {
    override suspend fun execute(): DataState<List<CollectionModel>> {
        return getDataState(tvHomeRepository.getTvCollection()) { list ->
            list?.map { it.toModel() }
        }
    }
}
