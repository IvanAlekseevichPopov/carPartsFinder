package com.jetpack.carpartsfinder.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jetpack.carpartsfinder.network.PartResponse
import com.jetpack.carpartsfinder.ui.theme.Purple500

//@ExperimentalMaterialApi
@Composable
fun PartsScreenView(
    parts: List<PartResponse>?,
    onSearchPress: (String) -> Unit,
    onCardPress: (Int) -> Unit, //TODO uuid of part
) {
//    val scope = rememberCoroutineScope()
//    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
//    val screenState = viewModel.uiState.collectAsState()
//    val partsData = viewModel.getPartsData.observeAsState()
//    val parts = runBlocking {
//        repo.getParts(null).data
//    }

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = scaffoldState
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Purple500)
                        .padding(16.dp)
                ) {
                    val localInputState = remember { mutableStateOf("") }
                    TextField(
                        value = localInputState.value,
                        onValueChange = {
                            localInputState.value = it
                        },
                        trailingIcon = {
                            IconButton(onClick = { onSearchPress(localInputState.value) }) {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = "Search button"
                                )
                            }
                        }
                    )
                }

                if (parts == null) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                } else if (parts.isEmpty()) { //TODO when
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Can't find nothing" //TODO trans
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        items(parts.size) { index ->
                            PartItemView(parts[index], onCardPress)
                        }
                    }
                }
            }
        }
    }
}

//@Composable
//@Preview
//private fun PreviewPartsScreenView() {
//    return PartsScreenView(
//        parts = emptyList(),
//        onCardPress = {},
//        onSearchPress = {}
//    )
//}
