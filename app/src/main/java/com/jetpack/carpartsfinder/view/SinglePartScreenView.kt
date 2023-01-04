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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.carpartsfinder.network.PartResponse
import com.jetpack.carpartsfinder.ui.theme.Purple500

//@ExperimentalMaterialApi
@Composable
fun SinglePartScreenView(
    parts: List<PartResponse>?,
    onSearchChange: (String) -> Unit,
) {
//    val scope = rememberCoroutineScope()
//    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
//    val screenState = viewModel.uiState.collectAsState()
//    val partsData = viewModel.getPartsData.observeAsState()

//    val navController = rememberNavController()
//    NavHost(
//        navController = ,
//        graph =
//    )
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
                            IconButton(onClick = { onSearchChange(localInputState.value) }) {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = "Search button"
                                )
                            }
                        }
                    )
                }

                //TODO добавить, состояние с пустым списком(ничего не найдено)
                if (parts == null) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        items(parts.size) { index ->
                            PartItemView(parts[index])
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun PreviewSinglePartScreenView() {
    return PartsScreenView(
        parts = null
    ) {
        println("SDFADSF")
    }
}
