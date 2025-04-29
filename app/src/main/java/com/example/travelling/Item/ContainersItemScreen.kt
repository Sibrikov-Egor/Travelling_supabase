package com.example.travelling.Item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelling.R
import com.example.travelling.Tables.Categories

@Composable
fun TextFieldSearchItemScreen(
    txt: String,
    label: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = txt,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Color(0xFFF5F0FF),
            focusedContainerColor = Color(0xFFF5F0FF),
            cursorColor = Color(0xFF7A6A9C),
            unfocusedTextColor = Color(0xFF5E4B8B),
            focusedTextColor = Color(0xFF3A2C5A)
        ),
        label = {
            Text(
                text = label,
                color = Color(0xFF7A6A9C),
                fontSize = 14.sp
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFF5F0FF),
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = Color(0xFFD9D0EB),
                shape = RoundedCornerShape(12.dp)
            ),
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        textStyle = TextStyle(fontSize = 16.sp)
    )
}

@Composable
fun CatregoriesButton(typeCategories: Categories?, onClick: () -> Unit){
    Box(
        modifier = Modifier.
        border(
            width = 2.dp,
            color = Color(0xFF625b71),
            shape = RoundedCornerShape(0.dp)
        )
            .clickable(onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() })
            .padding(10.dp)
            .width(100.dp),
        contentAlignment = Alignment.Center,
    ){
        Text(text = typeCategories?.name ?: "", color = Color(0xFF6650a4), textAlign = TextAlign.Center)
    }
}

@Composable
fun CategoriesButton(
    typeCategories: Categories?,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .border(
                width = 2.dp,
                color = Color(0xFF7251AB),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
            .padding(10.dp)
            .width(100.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = typeCategories?.name ?: "",
            color = Color(0xFF7952EE),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun InterfaceButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(300.dp)
            .height(50.dp)
            .background(
                color = Color(0xFF8056C5),
                shape = RoundedCornerShape(4.dp)
            )
            .border(
                width = 2.dp,
                color = Color(0xFF795AE7),
                shape = RoundedCornerShape(4.dp)
            )
            .clickable(onClick = onClick)
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Подробнее",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp
        )
    }
}

@Composable
fun SozdatelButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(300.dp)
            .height(50.dp)
            .background(
                color = Color(0xFF4E3C9D),
                shape = RoundedCornerShape(4.dp)
            )
            .border(
                width = 2.dp,
                color = Color(0xFF6855CE),
                shape = RoundedCornerShape(4.dp)
            )
            .clickable(onClick = onClick)
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Создать",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
    }
}
