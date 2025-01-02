package com.rezaie.domain.domainCharacteres.usecase

import com.rezaie.core.IoDispatcher
import com.rezaie.domain.domainCharacteres.base.FlowUseCase
import com.rezaie.domain.domainCharacteres.base.Resource
import com.rezaie.domain.domainCharacteres.entity.CharacterDetailEntity
import com.rezaie.domain.domainCharacteres.entity.CharacterEntity
import com.rezaie.domain.domainCharacteres.repository.CharacterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCharacterDetailUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : FlowUseCase<CharacterEntity, CharacterDetailEntity?>(dispatcher) {

    override fun execute(parameters: CharacterEntity): Flow<Resource<CharacterDetailEntity?>> {
        return characterRepository.getCharacterDetail(parameters).map {
            Resource.Success(it)
        }
    }
}