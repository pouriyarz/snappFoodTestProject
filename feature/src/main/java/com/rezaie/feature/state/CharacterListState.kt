package com.rezaie.feature.state

import com.rezaie.core.constants.Queue
import com.rezaie.domain.domainCore.ProgressBarState
import com.rezaie.domain.domainCore.UIComponent
import com.rezaie.feature.presentation.CharacterView

data class CharacterListState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val movies: List<CharacterView> = listOf(),
    val api_key: String = "",
    val page: Int = 1,
    val isPageAvailable: Boolean = true,
    val errorQueue: Queue<UIComponent> = Queue(mutableListOf()),
)
