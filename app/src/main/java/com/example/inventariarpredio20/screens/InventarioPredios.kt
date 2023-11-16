package com.example.inventariarpredio20.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.inventariarpredio20.R
import com.example.inventariarpredio20.data.repository.MDURepository
import com.example.inventariarpredio20.data.repository.PredioRepository


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
    var textValue2 by remember { mutableStateOf("") }
    var textValue3 by remember { mutableStateOf("") }
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
                    .padding(start = 13.dp, top=13.dp)
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

            FiltroSeleccionInventario()

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

            Button(onClick = {},colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray), modifier = Modifier.width(150.dp).height(40.dp)
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
            Spacer(modifier = Modifier.width(35.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FiltroSeleccionInventario(){
    var textValue by remember { mutableStateOf(TextFieldValue("")) }
    var selectedName by remember { mutableStateOf("") }

    val names = remember {
        mutableStateListOf<String>()
    }

    // Llenar la lista de nombres de predios al iniciar la composición
    LaunchedEffect(key1 = true) {
        val mdu = MDURepository.obtenerTipoMDU()
        names.clear()
        names.addAll(mdu)
    }

    val searchText = textValue.text
    val filteredNames = names.filter { it.contains(searchText, ignoreCase = true) }

    Column {
        OutlinedTextField(
            value = textValue,
            onValueChange = {
                textValue = it
                if (it.text.isEmpty()) {
                    selectedName = ""
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    selectedName = textValue.text
                }
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black, // Color del texto
                cursorColor = Color.Black, // Color del cursor
                focusedBorderColor = Color.Black.copy(alpha = 0.8f), // Color del borde cuando enfocado
                unfocusedBorderColor = Color.Black.copy(alpha = 0.4f) // Color del borde cuando no está enfocado
            ),
            modifier = Modifier
                .width(350.dp)
                .fillMaxWidth()
                .then(Modifier.align(Alignment.Start))
                .border(
                    width = 1.dp,
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(8.dp)
                )
                .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp))
        )
        if (searchText.isNotBlank() && selectedName.isEmpty()) {
            LazyColumn(modifier = Modifier.width(350.dp).background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp))) {
                items(filteredNames) { name ->
                    Text(
                        text = name,
                        fontSize = 18.sp,
                        color = Color(0xFF000000),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable {
                                selectedName = name
                                textValue = textValue.copy(
                                    text = name,
                                    selection = TextRange(name.length, name.length)
                                )
                            }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InventarioPrediosPreview(){
    val navController = rememberNavController()
    InventarioPredios(navController)
}