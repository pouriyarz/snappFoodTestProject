package com.rezaie.feature.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rezaie.components.R as componentsR

@Composable
fun CharacterListToolbar(
    onTextChange: (String) -> Unit = {},
    query: String
) {

    LaunchedEffect(query) {
        onTextChange(query)
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 0.dp,
    ) {
        Row(
            modifier = Modifier
                .padding(all = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .border(
                        0.dp,
                        MaterialTheme.colors.onBackground.copy(alpha = .5f),
                        shape = RoundedCornerShape(12.dp)
                    ),
                elevation = 0.dp,
                shape = RoundedCornerShape(12.dp),
                backgroundColor = if (MaterialTheme.colors.isLight) Color.LightGray else Color.DarkGray,
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = stringResource(componentsR.string.search),
                        tint = MaterialTheme.colors.onBackground,
                        modifier = Modifier
                            .padding(start = 18.dp)
                            .size(25.dp),
                    )
                    TextField(
                        value = query,
                        onValueChange = {
                            onTextChange(it)
                        },
                        placeholder = {
                            Text(
                                text = stringResource(componentsR.string.search),
                                style = MaterialTheme.typography.button.merge(
                                    TextStyle(fontWeight = FontWeight.Normal, fontSize = 16.sp)
                                ),
                                color = Color.Gray
                            )
                        },
                        textStyle = MaterialTheme.typography.body1.merge(
                            TextStyle(fontWeight = FontWeight.Normal, fontSize = 16.sp)
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            cursorColor = MaterialTheme.colors.onBackground,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                        singleLine = true
                    )
                }
            }
        }
    }
}



