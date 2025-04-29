package com.example.travelling.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.travelling.Navigation.Navigate
import com.example.travelling.Navigation.NavigationRoutes
import com.example.travelling.State.TravellingDataClass
import com.example.travelling.ViewModel.SozdatView


@Composable
fun SozdatelScreen(navController: NavController, viewModel: SozdatView = viewModel()){

    val categories = viewModel.categories.observeAsState(emptyList())
    val selectedCategory = remember { mutableStateOf("") }

    val travellingDataClass = viewModel.travellingDataClass

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
            TextFieldAdd(text = travellingDataClass.name, label = "Название места",
                onValueChanged = { viewModel.updateTravelling(travellingDataClass.copy(name = it)) })
            TextFieldAdd(text = travellingDataClass.Country, label = "Страна",
                onValueChanged = { viewModel.updateTravelling(travellingDataClass.copy(Country = it)) })
            TextFieldAdd(text = travellingDataClass.description, label = "Описание",
                onValueChanged = { viewModel.updateTravelling(travellingDataClass.copy(description = it)) })
            LazyRow {
                items(categories.value.indices.toList()) { index ->
                    CategoriessButton(
                        typeCategories = categories.value[index].copy(),
                        onClick = {
                            selectedCategory.value = categories.value[index].id
                            viewModel.updateTravelling(travellingDataClass.copy(categoryId = selectedCategory.value))
                        }
                    )
                }
            }
            TextFieldCategoriesAdd(text = selectedCategory.value)
            InterfaceButtonAdd(text = "Добавить", onClick = {viewModel.addTravelling()
                navController.navigate(NavigationRoutes.ListItem)})
        }
    }
}