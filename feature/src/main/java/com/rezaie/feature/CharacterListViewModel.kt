package com.rezaie.feature

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.rezaie.core.constants.Logger
import com.rezaie.domain.domainCharacteres.repository.CharacterRepository
import com.rezaie.domain.domainCharacteres.usecase.GetCharactersUseCase
import com.rezaie.domain.domainCore.DataState
import com.rezaie.domain.domainCore.UIComponent
import com.rezaie.feature.mapper.toCharacterView
import com.rezaie.feature.presentation.CharacterView
import com.rezaie.feature.state.CharacterListEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel
@Inject
constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _characters = MutableStateFlow<PagingData<CharacterView>>(PagingData.empty())
    val characters: StateFlow<PagingData<CharacterView>> = _characters.asStateFlow()

    private val _query = MutableStateFlow("")
    private val query: StateFlow<String> = _query.asStateFlow()

    init {
        setupSearch()
    }

    fun updateQuery(newQuery: String) {
        _query.value = newQuery
    }

    private var searchJob: Job? = null

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    private fun setupSearch() {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            query.debounce(300).distinctUntilChanged().flatMapLatest { searchQuery ->
                _characters.value = PagingData.empty()
                getCharactersUseCase(
                    GetCharactersUseCase.Params(
                        searchQuery, PagingConfig(
                            pageSize = PAGE_SIZE,
                            initialLoadSize = INITIAL_LOAD_SIZE
                        )
                    )
                )
                    .cachedIn(viewModelScope)
                    .map { pagingData ->
                        pagingData.map {
                            it.toCharacterView()
                        }
                    }
            }
                .collect { pagingData ->
                    _characters.value = pagingData
                }
        }
    }

    companion object {
        private const val PAGE_SIZE = 10
        private const val INITIAL_LOAD_SIZE = 10
    }
}