package com.ozturkurtulus.composeintro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen(){
    //Column, Row, Box
    Column() {
        Text(modifier = Modifier.background(color = Color.LightGray)
            .padding(top = 10.dp)
            .clickable {
                println("tıkladın")
            },
            text = "Hello World!",
            color = Color.Blue , fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold)
        Text(text = "Hello Android!",
            color = Color.Blue,
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}