package com.example.travelling.Item

import androidx.compose.foundation.Image
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
        onValueChange = {onValueChange(it)},
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            cursorColor = Color(0xff9b2d30),
            unfocusedTextColor = Color(0xff9b2d30),
            focusedTextColor = Color(0xff9b2d30)
        ),
        label = {
            Text(text = label, color = Color(0xff9b2d30))
        },
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, Color(0xff9b2d30), RoundedCornerShape(0.dp)),
        maxLines = 1
    )
}

@Composable
fun CatregoriesButton(typeCategories: Categories?, onClick: () -> Unit){
    Box(
        modifier = Modifier.
        border(
            width = 2.dp,
            color = Color(0xff9b2d30),
            shape = RoundedCornerShape(0.dp)
        )
            .clickable(onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() })
            .padding(10.dp)
            .width(100.dp),
        contentAlignment = Alignment.Center,
    ){
        Text(text = typeCategories?.name ?: "", color = Color(0xff9b2d30), textAlign = TextAlign.Center)
    }
}

@Composable
fun AllCatregoriesButton(onClick: () -> Unit){
    Box(
        modifier = Modifier.
        border(
            width = 2.dp,
            color = Color(0xff9b2d30),
            shape = RoundedCornerShape(0.dp)
        )
            .clickable(onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() })
            .padding(10.dp)
            .width(100.dp),
        contentAlignment = Alignment.Center,
    ){
        Text(text = "Все")
    }
}
