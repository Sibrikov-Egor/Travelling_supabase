package com.example.travelling.Item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.travelling.R


@Composable
fun ListItem(navController: NavController) {
    Column(modifier = androidx.compose.ui.Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        Image(
            painter = painterResource(id = R.drawable.logo), contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
                .clip(RoundedCornerShape(15.dp))

        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Горы",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Описание",
            color = Color.Gray,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "1000 рублей",
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }}
//    Card(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(10.dp),
//        shape = RoundedCornerShape(15.dp),
//
//    )  {
//
//            Image(
//                painter = painterResource(id = R.drawable.logo),
//                contentDescription = "logo",
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .padding(5.dp)
//                    .size(64.dp)
//                    .clip(CircleShape)
//            )
//        }

