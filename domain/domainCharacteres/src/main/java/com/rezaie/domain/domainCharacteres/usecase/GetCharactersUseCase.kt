package com.rezaie.domain.domainCharacteres.usecase

import androidx.paging.PagingData
import com.rezaie.core.IoDispatcher
import com.rezaie.domain.base.PagingDataUseCase
import com.rezaie.domain.domainCharacteres.entity.CharacterEntity
import com.rezaie.domain.domainCharacteres.repository.CharacterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : PagingDataUseCase<GetCharactersUseCase.Params, CharacterEntity>(dispatcher) {

    override fun execute(input: Params): Flow<PagingData<CharacterEntity>> {
        return characterRepository.getCharacters(input.query)
    }

    data class Params(val query: String)
}