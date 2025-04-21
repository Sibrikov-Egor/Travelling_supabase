package com.example.travelling.Item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.travelling.R
import com.example.travelling.Tables.Travells
import java.lang.reflect.Modifier

@Composable
fun TravellCard(travells: Travells){
    Box(

    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterVertically))
        {
            Text(
                text = travells.name,
                fontSize = 20.sp,
                color = Color(0xff9b2d30),
                fontWeight = FontWeight.Bold
            )
            AsyncImage(
                model = travells.image,
                contentDescription = "",

                error = painterResource(R.drawable.logo),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = travells.description,
                fontSize = 15.sp,
                color = Color(0xff9b2d30),
                textAlign = TextAlign.Justify,
            )
        }
    }
}