package com.rezaie.domain.domainCore

/**
 * to handle progressbar loading.
 * */
sealed class ProgressBarState{

    data object Loading: ProgressBarState()

    data object Idle: ProgressBarState()

}