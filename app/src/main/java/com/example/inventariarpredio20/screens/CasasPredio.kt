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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.inventariarpredio20.R
import com.example.inventariarpredio20.data.repository.CasaRepository
import com.example.inventariarpredio20.models.Predio
import com.example.inventariarpredio20.models.PredioCasa
import com.example.inventariarpredio20.navigation.AppScreens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CasasPredio(navController: NavController, idPredio: String){
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
        ContenidoCasasPredio(navController, idPredio)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContenidoCasasPredio(navController: NavController, idPredio: String){
    var textValue1 by remember { mutableStateOf("") }
    var listaCasas by remember { mutableStateOf(emptyList<PredioCasa>()) }
    val cargando = remember { mutableStateOf(false) }
    Box(modifier = Modifier.padding(start = 16.dp, top = 76.dp, end = 16.dp, bottom = 16.dp)){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue, shape = RoundedCornerShape(size = 16.dp)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Casas del Predio",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 13.dp, top = 13.dp)
                    .then(Modifier.align(Alignment.Start))
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text("Tipo de Unidad de Vivienda",
                color = Color.White,
                fontSize = 15.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp)
            )
            Text("Múltiple",
                color = Color.White,
                fontSize = 15.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp)
            )

            Spacer(modifier = Modifier.height(5.dp))

            Column{

                OutlinedTextField(
                    value = textValue1,
                    onValueChange = { newValue ->
                        textValue1 = newValue
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

                Spacer(modifier = Modifier.width(12.dp))

                Button(
                    onClick = {
                        cargando.value = true

                },colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray), modifier = Modifier
                    .width(120.dp)
                    .height(40.dp)
                    .padding(start = 15.dp, top=5.dp)
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
                    val casasFiltradas = withContext(Dispatchers.Default) {
                        filtrarCasasPorIdMDU(textValue1, idPredio)
                    }
                    listaCasas = casasFiltradas
                }

                LazyColumn {
                    items(listaCasas) { casa->
                        TarjetaCasa(casa = casa, navController)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }



        }
    }
}

suspend fun filtrarCasasPorIdMDU(idMDU: String, idPredio: String): List<PredioCasa> {
    return CasaRepository.obtenerCasasPorIdMDU(idMDU, idPredio)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FiltroTipoPredioCasas(){
    var textValue by remember { mutableStateOf(TextFieldValue("")) }
    var selectedName by remember { mutableStateOf("") }

    val names = remember {
        mutableStateListOf(
            "Alice",
            "Bob",
            "Charlie",
            "David",
            "Eva",
            "Frank",
            "Grace",
            "Hannah",
            "Isaac",
            "Jack"
        )
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
            modifier = Modifier
                .width(230.dp)
                .padding(start = 4.dp)
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
            LazyColumn {
                items(filteredNames) { name ->
                    Text(
                        text = name,
                        fontSize = 18.sp,
                        color = Black,
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
fun CasasPredioPreview(){
    val navController = rememberNavController()
    CasasPredio(navController, "")
}

@Composable
fun TarjetaCasa(casa: PredioCasa, navController: NavController) {

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
            Text(text = "ID: ${casa.idCasa}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "ID del Predio: ${casa.idPredio}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "ID del Estado: ${casa.idEstado}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "ID del Predio MDU: ${casa.idPredioMdu}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Número: ${casa.numero}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Piso: ${casa.piso ?: "N/A"}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Área: ${casa.area}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Participación: ${casa.participacion ?: "N/A"}")
            Spacer(modifier = Modifier.height(15.dp))

            Row(){
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