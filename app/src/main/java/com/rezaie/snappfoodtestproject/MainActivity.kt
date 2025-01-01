package com.rezaie.snappfoodtestproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.Text
import androidx.compose.ui.unit.dp
import com.rezaie.snappfoodtestproject.ui.theme.BaseTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseTheme {
                BoxWithConstraints {
                    if (maxWidth > 600.dp) {
                        Text("Large screen layout")
                    } else {
                        Text("Small screen layout")
                    }
                }
            }
        }
    }
}