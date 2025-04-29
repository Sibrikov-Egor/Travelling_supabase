package com.example.travelling.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelling.Tables.Categories

@Composable
fun InterfaceButtonAdd(
    onClick: () -> Unit,
    text: String
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .border(
                width = 3.dp,
                color = Color(0xFF7660DC),
                shape = RoundedCornerShape(5.dp)
            )
            .background(
                color = Color(0xFF7D5EB4),
                shape = RoundedCornerShape(5.dp)
            )
            .clickable(onClick = onClick)
            .width(100.dp)
            .height(35.dp)
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp
        )
    }
}


@Composable
fun TextFieldAdd(text: String, label: String, onValueChanged: (String) -> Unit){
    TextField(
        value = text,
        onValueChange = {
            onValueChanged(it)
        },
        shape = RoundedCornerShape(15.dp),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            cursorColor = Color(0xFF6460A4),
            unfocusedTextColor = Color(0xFF8482D0),
            focusedTextColor = Color(0xFF8A54D7)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFF6E6AAB), RoundedCornerShape(15.dp)),
        label = {
            Text(text = label, color = Color(0xFF674AEE))
        }
    )
}

@Composable
fun TextFieldCategoriesAdd(text: String){
    TextField(
        value = text,
        onValueChange = {
        },
        enabled = false,
        shape = RoundedCornerShape(15.dp),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            cursorColor = Color(0xFF806AE5),
            unfocusedTextColor = Color(0xFFAB6CF6),
            focusedTextColor = Color(0xFF7950EA)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFF8E61D9), RoundedCornerShape(15.dp)),
    )
}

@Composable
fun CategoriessButton(
    typeCategories: Categories?,
    onClick: () -> Unit,
    isSelected: Boolean = false
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = if (isSelected) Color(0xFF6A4DBC) else Color.White,
                    shape = RoundedCornerShape(16.dp)
                )
                .border(
                    width = 1.dp,
                    color = if (isSelected) Color(0xFF6A4DBC) else Color(0xFFE0E0E0),
                    shape = RoundedCornerShape(16.dp)
                )
                .clickable(
                    onClick = onClick,

                )
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = typeCategories?.name ?: "",
                color = if (isSelected) Color.White else Color(0xFF46407E),
                fontSize = 14.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
}