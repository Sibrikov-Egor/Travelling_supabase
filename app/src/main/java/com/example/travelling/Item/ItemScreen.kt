package com.example.travelling.Item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.travelling.Navigation.NavigationRoutes
import com.example.travelling.R
import com.example.travelling.State.ResultDataClass
import com.example.travelling.Tables.Travelling
import com.example.travelling.ViewModel.ItemView


@Composable
fun ListItem(navController: NavController, itemview: ItemView = viewModel()) {
    val timeState by itemview.resultDataClass.collectAsState()
    val travelling = itemview.travelling.observeAsState(emptyList())
    val categories = itemview.categories.observeAsState(emptyList())
    val txtSearch = remember { mutableStateOf("") }
    val CategorySelect = remember { mutableStateOf("") }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(
            top = 40.dp
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextFieldSearchItemScreen(txt = txtSearch.value, onValueChange = {newText ->
            txtSearch.value = newText
            itemview.filterListTravells(newText, CategorySelect.value)
        },
            label = "Поиск")

        when(timeState) {
            is ResultDataClass.Error ->
                Text(text = (timeState as ResultDataClass.Error).message)

            ResultDataClass.Initialized -> TODO()
            is ResultDataClass.Loading -> {
                Box(
                    modifier = Modifier.size(100.dp)
                ) {
                    LinearProgressIndicator(color = Color(0xFF7961C9))
                }
            }

            is ResultDataClass.Success -> {
                LazyRow {
                    items(categories.value.indices.toList()) { index ->
                        CatregoriesButton(typeCategories = categories.value[index].copy(),
                            onClick = {
                                CategorySelect.value = categories.value[index].id
                                itemview.filterListTravells(
                                    txtSearch.value,
                                    categoryId = CategorySelect.value
                                )
                            }
                        )
                    }
                }
                SozdatelButton(onClick = {
                    navController.navigate(NavigationRoutes.SozdatelScreen)
                })
                LazyColumn {
                    items(travelling.value) { it ->
                        TravellCard(travelling = it)
                        InterfaceButton(onClick = {
                            navController.navigate(NavigationRoutes.InterfaceScreen + "/" + it.id)
                        })
                    }
                }
            }
        }
    }
}

