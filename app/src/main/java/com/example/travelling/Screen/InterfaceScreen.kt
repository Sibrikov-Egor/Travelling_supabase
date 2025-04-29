package com.example.travelling.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

import coil.compose.AsyncImage
import com.example.travelling.Navigation.NavigationRoutes
import com.example.travelling.R
import com.example.travelling.State.ResultDataClass
import com.example.travelling.State.TravellingDataClass
import com.example.travelling.ViewModel.InterfaceViewModel


@Composable
fun InterfaceScreen(navController: NavController, id:String,
                       viewModel: InterfaceViewModel = viewModel {InterfaceViewModel(id)},
) {
    val resultSelect by viewModel.resultDataClassSelectTravelling.collectAsState()

    val categories = viewModel.categories.observeAsState(emptyList())
    val selectedCategory = remember { mutableStateOf("") }

    val userId = viewModel.userId
    val travellingDataClass = viewModel.travellingDataClass

    when(resultSelect){
        is ResultDataClass.Error -> {
            Text(text = "ОШИБКА")
        }
        is ResultDataClass.Loading -> {
            Box(
                modifier = Modifier.size(100.dp)
            ) {
                LinearProgressIndicator(color = Color(0xFF7A60D9))
            }
        }
        is ResultDataClass.Initialized -> {}
        is ResultDataClass.Success -> {
            LazyColumn {
                item{

                    if(userId == travellingDataClass.Sozdatel){
                        Box(

                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(
                                    10.dp,
                                    alignment = Alignment.CenterVertically
                                ),
                                modifier = Modifier.padding(
                                    end = 10.dp,
                                    start = 10.dp,
                                    bottom = 15.dp,
                                    top = 50.dp
                                )
                            )
                            {
                                AsyncImage(
                                    model = travellingDataClass.image,
                                    contentDescription = "",
                                    modifier = Modifier.fillMaxWidth(),
                                    error = painterResource(R.drawable.logo),
                                    contentScale = ContentScale.Crop,
                                )
                                TextFieldUpdate(text = travellingDataClass.name, label = "Название места",
                                    onValueChanged = {viewModel.updateTravelling(travellingDataClass.copy(name = it))})
                                TextFieldUpdate(text = travellingDataClass.Country, label = "Страна",
                                    onValueChanged = {viewModel.updateTravelling(travellingDataClass.copy(Country = it))})
                                TextFieldUpdate(text = travellingDataClass.description, label = "Описание",
                                    onValueChanged = {viewModel.updateTravelling(travellingDataClass.copy(description = it))})
                                LazyRow {
                                    items(categories.value.indices.toList()) { index ->
                                        CategoriesButton(
                                            typeCategories = categories.value[index].copy(),
                                            onClick = {selectedCategory.value = categories.value[index].id
                                                viewModel.updateTravelling(travellingDataClass.copy(categoryId = selectedCategory.value))}
                                        )
                                    }
                                }
                                TextFieldCategoriesUpdate(text = selectedCategory.value)
                                Row {
                                   InterfaceButtonDelUpd(text = "Удалить", onClick = {viewModel.DeleteTravelling()
                                        navController.navigate(NavigationRoutes.ListItem)})
                                    InterfaceButtonDelUpd(text = "Обновить", onClick = {viewModel.UpdateTravelling()})
                                }
                            }
                        }

                    }
                    else {
                        Box(

                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(
                                    10.dp,
                                    alignment = Alignment.CenterVertically
                                ),
                                modifier = Modifier.padding(
                                    end = 10.dp,
                                    start = 10.dp,
                                    bottom = 15.dp,
                                    top = 50.dp
                                )
                            )
                            {
                                AsyncImage(
                                    model = travellingDataClass.image,
                                    contentDescription = "",
                                    modifier = Modifier.fillMaxWidth(),
                                    error = painterResource(R.drawable.logo),
                                    contentScale = ContentScale.Crop,
                                )
                                Text(
                                    text = "Название места: " + travellingDataClass.name,
                                    fontSize = 15.sp,
                                    color = Color(0xFF6146AD),
                                    textAlign = TextAlign.Justify
                                )
                                Text(
                                    text = "Страна: " + travellingDataClass.Country,
                                    fontSize = 15.sp,
                                    color = Color(0xFF8950C9),
                                    textAlign = TextAlign.Justify
                                )
                                Text(
                                    text = "Описание: " + travellingDataClass.description,
                                    fontSize = 15.sp,
                                    color = Color(0xFF904DE0),
                                    textAlign = TextAlign.Justify,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}