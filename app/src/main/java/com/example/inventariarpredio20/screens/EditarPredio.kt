package com.example.inventariarpredio20.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.inventariarpredio20.R


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditarPredio(navController: NavController){
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "INVENTARIAR PREDIOS",
                        color = Color.White, // Color del título
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Black)   // Color de fondo de la barra superior
            )
        }
    ){
        ContenidoEditarPredio(navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContenidoEditarPredio(navController: NavController){
    var textValue1 by remember { mutableStateOf("") }
    var textValue2 by remember { mutableStateOf("") }
    var textValue3 by remember { mutableStateOf("") }
    var textValue4 by remember { mutableStateOf("") }
    var textValue5 by remember { mutableStateOf("") }
    var textValue6 by remember { mutableStateOf("") }
    var textValue7 by remember { mutableStateOf("") }
    var textValue8 by remember { mutableStateOf("") }
    Box(modifier = Modifier.padding(start = 16.dp, top = 76.dp, end = 16.dp, bottom = 16.dp)){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue, shape = RoundedCornerShape(size = 16.dp)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Editar Predio",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 13.dp, top=13.dp)
                    .then(Modifier.align(Alignment.Start))
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text("ID del predio                        ID del tipo de predio",
                color = Color.White,
                fontSize = 15.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp)
                    .then(Modifier.align(Alignment.Start))
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row{
                OutlinedTextField(
                    value = textValue1,
                    onValueChange = { newValue ->
                        textValue1 = newValue
                    },
                    modifier = Modifier
                        .width(175.dp)
                        .fillMaxWidth()
                        .padding(start = 1.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                    )

                Spacer(modifier = Modifier.width(2.dp))

                OutlinedTextField(
                    value = textValue2,
                    onValueChange = { newValue ->
                        textValue2 = newValue
                    },
                    modifier = Modifier
                        .width(175.dp)
                        .fillMaxWidth()
                        .padding(start = 11.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Text("Descripción del predio       RUC del predio",
                color = Color.White,
                fontSize = 15.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp)
                    .then(Modifier.align(Alignment.Start))
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row{
                OutlinedTextField(
                    value = textValue3,
                    onValueChange = { newValue ->
                        textValue3 = newValue
                    },
                    modifier = Modifier
                        .width(175.dp)
                        .fillMaxWidth()
                        .padding(start = 1.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                )

                Spacer(modifier = Modifier.width(2.dp))

                OutlinedTextField(
                    value = textValue4,
                    onValueChange = { newValue ->
                        textValue4 = newValue
                    },
                    modifier = Modifier
                        .width(175.dp)
                        .fillMaxWidth()
                        .padding(start = 11.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Text("Teléfono del predio            Correo del predio",
                color = Color.White,
                fontSize = 15.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp)
                    .then(Modifier.align(Alignment.Start))
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row{
                OutlinedTextField(
                    value = textValue5,
                    onValueChange = { newValue ->
                        textValue5 = newValue
                    },
                    modifier = Modifier
                        .width(175.dp)
                        .fillMaxWidth()
                        .padding(start = 1.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                )

                Spacer(modifier = Modifier.width(2.dp))

                OutlinedTextField(
                    value = textValue6,
                    onValueChange = { newValue ->
                        textValue6 = newValue
                    },
                    modifier = Modifier
                        .width(175.dp)
                        .fillMaxWidth()
                        .padding(start = 11.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Text("Dirección del predio           ID del ubigeo",
                color = Color.White,
                fontSize = 15.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp)
                    .then(Modifier.align(Alignment.Start))
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row{
                OutlinedTextField(
                    value = textValue7,
                    onValueChange = { newValue ->
                        textValue7 = newValue
                    },
                    modifier = Modifier
                        .width(175.dp)
                        .fillMaxWidth()
                        .padding(start = 1.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                )

                Spacer(modifier = Modifier.width(2.dp))

                OutlinedTextField(
                    value = textValue8,
                    onValueChange = { newValue ->
                        textValue8 = newValue
                    },
                    modifier = Modifier
                        .width(175.dp)
                        .fillMaxWidth()
                        .padding(start = 11.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Button(onClick = {},colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray), modifier = Modifier.width(200.dp).height(40.dp)
                .border(
                    width = 1.dp,
                    color = Color(0x00000000),
                    shape = RoundedCornerShape(16.dp)
                )
                .background(color = Color(0x00000000), shape = RoundedCornerShape(size = 16.dp))
                .fillMaxWidth()
            ){
                Text("Actualizar", color = White, fontSize = 15.sp)
            }
            Spacer(modifier = Modifier.width(35.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditarPredioPreview(){
    val navController = rememberNavController()
    EditarPredio(navController)
}