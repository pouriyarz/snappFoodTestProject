package com.rezaie.feature.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*

@Composable
@Preview
fun CharacterListToolbar(onTextChange: (String) -> Unit = {}) {
    var searchText by remember { mutableStateOf("") }

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
                    .border(1.5.dp, Color(0xFF6573B9), shape = RoundedCornerShape(12.dp)),
                elevation = 0.dp,
                shape = RoundedCornerShape(12.dp),
                backgroundColor = Color(0x549FA4C2),
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.Black,
                        modifier = Modifier.padding(start = 18.dp).size(30.dp),
                    )
                    TextField(
                        value = searchText,
                        onValueChange = {
                            searchText = it
                            onTextChange(it)
                        },
                        placeholder = {
                            Text(
                                text = "Search",
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
                            cursorColor = Color.Black,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 8.dp),
                        singleLine = true
                    )
                }
            }
        }
    }
}


