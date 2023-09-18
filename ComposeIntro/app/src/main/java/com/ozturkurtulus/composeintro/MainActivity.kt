package com.ozturkurtulus.composeintro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomText(string = "Hello")
        //space koyar
        Spacer(modifier = Modifier.padding(5.dp))
        CustomText(string = "Word")
        Spacer(modifier = Modifier.padding(5.dp))
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            CustomText(string = "Test1")
            CustomText(string = "Test2")
            CustomText(string = "Test3")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}
@Composable
fun CustomText(string: String){
    Text(modifier = Modifier
        .background(color = Color.LightGray)
        .padding(top = 10.dp)
        //.fillMaxWidth()
        .width(100.dp)
        .clickable {
            println("tıkladın")
        },
        textAlign = TextAlign.Center,
        text = string,
        color = Color.Blue , fontSize = 25.sp,
        fontWeight = FontWeight.ExtraBold)
}
