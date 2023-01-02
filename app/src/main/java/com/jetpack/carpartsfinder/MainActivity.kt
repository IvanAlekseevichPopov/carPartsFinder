package com.jetpack.carpartsfinder

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jetpack.carpartsfinder.ui.theme.CarPartsFinderTheme
import com.jetpack.carpartsfinder.ui.theme.Purple500
import com.jetpack.carpartsfinder.utils.Resource
import com.jetpack.carpartsfinder.view.PartItemView
import com.jetpack.carpartsfinder.viewmodel.PartViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarPartsFinderTheme {
                CallApi()
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun CallApi(
    viewModel: PartViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
//    val getAllUserData = viewModel.getUserData.observeAsState()
    val partsData = viewModel.getPartsData.observeAsState()

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
                        .padding(15.dp)
                ) {
                    Text(
                        text = "Parts",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }

                scope.launch {
                    val result = viewModel.getPartsData()

                    if (result is Resource.Success) {
                        Toast.makeText(context, "Fetching data success!", Toast.LENGTH_SHORT).show()
                    } else if (result is Resource.Error) {
                        Toast.makeText(context, "Error: ${result.message}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                if (!viewModel.isLoading.value) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                }

                if (viewModel.isLoading.value) {
                    if (viewModel.getPartsData.value!!.isNotEmpty()) {
                        LazyColumn(
                            modifier = Modifier.padding(10.dp)
                        ) {
//                            items(getAllUserData.value!!.size) { index ->
//                                UserListItem(getAllUserData.value!![index])
//                            }

                            items(partsData.value!!.size) { index ->
                                PartItemView(partsData.value!![index])
                            }
                        }
                    }
                }
            }
        }
    }
}