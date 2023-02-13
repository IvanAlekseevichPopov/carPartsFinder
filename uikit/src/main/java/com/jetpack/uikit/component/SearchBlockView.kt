package com.jetpack.uikit.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jetpack.uikit.theme.dimens

@Composable
fun SearchBlockView(
    onSearchPress: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .padding(MaterialTheme.dimens.sixteen)
    )
    {
        val localInputState = remember { mutableStateOf("") }
        TextField(
            value = localInputState.value,
            onValueChange = {
                localInputState.value = it
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        onSearchPress(localInputState.value)
                    },
                    content = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search button"
                        )
                    }
                )
            }
        )
    }
}
