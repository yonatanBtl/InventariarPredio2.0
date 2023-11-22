package com.example.inventariarpredio20.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.inventariarpredio20.data.repository.PredioRepository.filtrarPredios
import com.example.inventariarpredio20.data.repository.PredioRepository.obtenerDatosPredio
import com.example.inventariarpredio20.models.Predio
import com.example.inventariarpredio20.navigation.AppScreens



@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InventarioPredios(navController: NavController){
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
        ContenidoInventarioPredios(navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContenidoInventarioPredios(navController: NavController){
    var textValue by remember { mutableStateOf("") }
    var textValue2 by remember { mutableStateOf("") }
    var textValue3 by remember { mutableStateOf("") }

    var listaPredios by remember { mutableStateOf(emptyList<Predio>()) }
    val cargando = remember { mutableStateOf(false) }

    Box(modifier = Modifier.padding(start = 16.dp, top = 76.dp, end = 16.dp, bottom = 16.dp)){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue, shape = RoundedCornerShape(size = 16.dp)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Inventario de los Predios",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 13.dp, top = 13.dp)
                    .then(Modifier.align(Alignment.Start))
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text("Selección del tipo de predio",
                color = Color.White,
                fontSize = 15.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp)
                    .then(Modifier.align(Alignment.Start))
            )

            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                value = textValue,
                onValueChange = { newValue ->
                    textValue = newValue
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black, // Color del texto
                    cursorColor = Color.Black, // Color del cursor
                    focusedBorderColor = Color.Black.copy(alpha = 0.8f), // Color del borde cuando enfocado
                    unfocusedBorderColor = Color.Black.copy(alpha = 0.4f) // Color del borde cuando no está enfocado
                ),
                modifier = Modifier
                    .width(365.dp)
                    .fillMaxWidth()
                    .padding(start = 15.dp)
                    .then(Modifier.align(Alignment.Start))
                    .border(
                        width = 1.dp,
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp))
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text("Nombre del Predio",
                color = Color.White,
                fontSize = 15.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp)
                    .then(Modifier.align(Alignment.Start))
            )

            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                value = textValue2,
                onValueChange = { newValue ->
                    textValue2 = newValue
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black, // Color del texto
                    cursorColor = Color.Black, // Color del cursor
                    focusedBorderColor = Color.Black.copy(alpha = 0.8f), // Color del borde cuando enfocado
                    unfocusedBorderColor = Color.Black.copy(alpha = 0.4f) // Color del borde cuando no está enfocado
                ),
                modifier = Modifier
                    .width(365.dp)
                    .fillMaxWidth()
                    .padding(start = 15.dp)
                    .then(Modifier.align(Alignment.Start))
                    .border(
                        width = 1.dp,
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp))
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text("RUC del predio",
                color = Color.White,
                fontSize = 15.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp)
                    .then(Modifier.align(Alignment.Start))
            )

            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                value = textValue3,
                onValueChange = { newValue ->
                    textValue3 = newValue
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black, // Color del texto
                    cursorColor = Color.Black, // Color del cursor
                    focusedBorderColor = Color.Black.copy(alpha = 0.8f), // Color del borde cuando enfocado
                    unfocusedBorderColor = Color.Black.copy(alpha = 0.4f) // Color del borde cuando no está enfocado
                ),
                modifier = Modifier
                    .width(365.dp)
                    .fillMaxWidth()
                    .padding(start = 15.dp)
                    .then(Modifier.align(Alignment.Start))
                    .border(
                        width = 1.dp,
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp))
            )

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = {
                    cargando.value = true
                }
                ,colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray), modifier = Modifier
                    .width(150.dp)
                    .height(40.dp)
                    .padding(start = 15.dp)
                    .then(Modifier.align(Alignment.Start))
                    .border(
                        width = 1.dp,
                        color = Color(0x00000000),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .background(color = Color(0x00000000), shape = RoundedCornerShape(size = 16.dp))
                    .fillMaxWidth()
            ){
                Text("Filtrar", color = White, fontSize = 15.sp)
            }

            LaunchedEffect(cargando.value) {
                if (cargando.value) {
                    val tipoPredio = if (textValue.isNotBlank()) textValue else null
                    val nombrePredio = if (textValue2.isNotBlank()) textValue2 else null
                    val rucPredio = if (textValue3.isNotBlank()) textValue3 else null

                    // Realizar el filtrado de predios con los valores ingresados
                    val prediosFiltrados = filtrarPredios(tipoPredio, nombrePredio, rucPredio)
                    listaPredios = prediosFiltrados
                    cargando.value = false
                }
            }

            LazyColumn {
                items(listaPredios) { predio->
                    TarjetaPredio(predio = predio, navController)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            Spacer(modifier = Modifier.width(35.dp))
        }
    }
}



@Preview(showBackground = true)
@Composable
fun InventarioPrediosPreview(){
    val navController = rememberNavController()
    InventarioPredios(navController)
}

suspend fun obtenerTodosLosPredios(): List<Predio> {
    return obtenerDatosPredio()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun filtrarTipoPredio() {
    var isExpanded by remember { mutableStateOf(false) }
    var gender by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it}
        ) {
            TextField(
                value = gender,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                DropdownMenuItem(
                    text = {
                        Text(text = "Condominio") },
                    onClick = {
                        gender = "Condominio"
                        isExpanded = false
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text(text = "Residencial") },
                    onClick = {
                        gender = "Residencial"
                        isExpanded = false
                    }
                )
            }
        }
    }
}



@Composable
fun TarjetaPredio(predio: Predio, navController: NavController) {

    val elevacionDp = 8.dp
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = "ID: ${predio.idPredio}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Tipo de Predio: ${predio.idTipoPredio}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Descripción: ${predio.descripcion}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "RUC: ${predio.ruc}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Teléfono: ${predio.telefono}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Correo: ${predio.correo}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Dirección: ${predio.direccion}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Ubigeo: ${predio.idUbigeo}")
            Spacer(modifier = Modifier.height(15.dp))

            Row(){
                Button(
                    onClick = {
                        navController.navigate(route = AppScreens.CasasPredio.route)
                    }
                    ,colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue), modifier = Modifier
                        .width(130.dp)
                        .height(40.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0x00000000),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .background(
                            color = Color(0x00000000),
                            shape = RoundedCornerShape(size = 16.dp)
                        )
                        .fillMaxWidth()
                ){
                    Text("Mostrar Casas", color = White, fontSize = 10.sp)
                }
                Button(
                    onClick = {
                        navController.navigate(route = AppScreens.EditarPredio.route)
                    }
                    ,colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White), modifier = Modifier
                        .width(105.dp)
                        .height(40.dp)
                        .padding(start = 25.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0x00000000),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .background(
                            color = Color(0x00000000),
                            shape = RoundedCornerShape(size = 16.dp)
                        )
                        .fillMaxWidth()
                ){
                    Text("Editar", color = Black, fontSize = 10.sp)
                }
                Button(
                    onClick = {

                    }
                    ,colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red), modifier = Modifier
                        .width(105.dp)
                        .height(40.dp)
                        .padding(start = 5.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0x00000000),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .background(
                            color = Color(0x00000000),
                            shape = RoundedCornerShape(size = 16.dp)
                        )
                        .fillMaxWidth()
                ){
                    Text("Eliminar", color = White, fontSize = 10.sp)
                }
            }
        }

    }
}
