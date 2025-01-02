package com.rezaie.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rezaie.core.constants.Queue
import com.rezaie.domain.domainCore.ProgressBarState
import com.rezaie.domain.domainCore.UIComponent

@Composable
fun BaseScreen(
    queue: Queue<UIComponent> = Queue(mutableListOf()), //Dialogs
    progressBarState: ProgressBarState = ProgressBarState.Idle,
    content: @Composable () -> Unit,// The content of the UI.
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            content()
            // process the queue
            if (!queue.isEmpty()) {
                queue.peek()?.let { uiComponent ->
                    if (uiComponent is UIComponent.Dialog) {
                        GenericDialog(
                            modifier = Modifier
                                .fillMaxWidth(0.9f),
                            title = uiComponent.title,
                            description = uiComponent.description,
                        )
                    }
                }
            }
        }
    }
}











