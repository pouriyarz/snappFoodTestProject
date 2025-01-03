package com.rezaie.feature

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.rezaie.domain.domainCharacteres.base.Resource
import com.rezaie.domain.domainCharacteres.base.onError
import com.rezaie.domain.domainCharacteres.base.onSuccess
import com.rezaie.domain.domainCharacteres.usecase.GetCharacterDetailUseCase
import com.rezaie.domain.domainCharacteres.usecase.GetCharactersUseCase
import com.rezaie.feature.mapper.toCharacterDetailView
import com.rezaie.feature.mapper.toCharacterEntity
import com.rezaie.feature.mapper.toCharacterView
import com.rezaie.feature.presentation.CharacterDetailView
import com.rezaie.feature.presentation.CharacterView
import com.rezaie.feature.state.CharacterListEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel
@Inject
constructor(
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase
) : ViewModel() {

    private val _characterDetail = MutableStateFlow<CharacterDetailView?>(CharacterDetailView.empty())
    val characterDetail: StateFlow<CharacterDetailView?> = _characterDetail.asStateFlow()

    private val _hasError = mutableStateOf(false)
    val hasError: State<Boolean> get() = _hasError

    private var characterDetailJob: Job? = null

    fun getCharacterDetail(characterView: CharacterView) {
        characterDetailJob?.cancel()
        characterDetailJob = viewModelScope.launch {
            getCharacterDetailUseCase(characterView.toCharacterEntity()).collectLatest { resource ->
                resource.onSuccess {
                    _characterDetail.value = it?.toCharacterDetailView()
                }
                resource.onError {
                    _hasError.value = true
                }
            }
        }
    }

    fun clearError() {
        _hasError.value = false
    }
}