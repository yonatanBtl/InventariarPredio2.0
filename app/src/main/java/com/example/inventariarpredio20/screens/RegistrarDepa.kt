package com.example.inventariarpredio20.screens

import android.annotation.SuppressLint
import android.util.Log
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
import com.example.inventariarpredio20.data.repository.CasaRepository
import com.example.inventariarpredio20.data.repository.MDURepository
import com.example.inventariarpredio20.data.repository.PredioRepository
import com.example.inventariarpredio20.models.PredioCasa
import com.example.inventariarpredio20.models.PredioMDU
import com.example.inventariarpredio20.navigation.AppScreens
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.math.BigDecimal


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegistrarDepa(navController: NavController){
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
        ContenidoRegistrarDepa(navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContenidoRegistrarDepa(navController: NavController){
    var textValue3 by remember { mutableStateOf("") }
    var textValue4 by remember { mutableStateOf("") }
    var textValue5 by remember { mutableStateOf("") }
    var textValue6 by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }
    var registroExitoso by remember { mutableStateOf(false) }
    Box(modifier = Modifier.padding(start = 16.dp, top = 76.dp, end = 16.dp, bottom = 16.dp)){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue, shape = RoundedCornerShape(size = 16.dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Registrar Departamento",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 13.dp)
                    .then(Modifier.align(Alignment.Start))
            )

            Spacer(modifier = Modifier.height(5.dp))

            Card(
                modifier = Modifier
                    .size(150.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(16.dp)
                    ), // Ajusta el tamaño de la imagen
                shape = RoundedCornerShape(16.dp), // Redondea las esquinas
            ) {
                Image(
                    painter = painterResource(id = R.drawable.departamento), // Reemplaza "nombre_de_la_imagen" con el nombre de tu imagen en recursos
                    contentDescription = null, // Puedes proporcionar una descripción apropiada
                    modifier = Modifier
                        .size(150.dp) // Ajusta el tamaño de la imagen según tus necesidades
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            Text("Selección del predio",
                color = Color.White,
                fontSize = 15.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp)
                    .then(Modifier.align(Alignment.Start))
            )

            Spacer(modifier = Modifier.height(5.dp))

            val nombrePredioSeleccionado = FiltroSeleccionPredioDepa()

            Spacer(modifier = Modifier.height(5.dp))

            Text("Estado del departamento",
                color = Color.White,
                fontSize = 15.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp)
                    .then(Modifier.align(Alignment.Start))
            )

            Spacer(modifier = Modifier.height(5.dp))

            val nombreEstadoCasaSeleccionado = FiltroEstadoDepa()

            Spacer(modifier = Modifier.height(5.dp))


            Text("Núm. del departamento  Piso del departamento",
                color = Color.White,
                fontSize = 15.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp)
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row{
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
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Black, // Color del texto
                        cursorColor = Color.Black, // Color del cursor
                        focusedBorderColor = Color.Black.copy(alpha = 0.8f), // Color del borde cuando enfocado
                        unfocusedBorderColor = Color.Black.copy(alpha = 0.4f) // Color del borde cuando no está enfocado
                    ),
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

            Spacer(modifier = Modifier.height(5.dp))

            Text("Área del departamento",
                color = Color.White,
                fontSize = 15.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp)
                    .then(Modifier.align(Alignment.Start))
            )

            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                value = textValue4,
                onValueChange = { newValue ->
                    textValue4 = newValue
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

            Text("Participación del departamento",
                color = Color.White,
                fontSize = 15.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp)
                    .then(Modifier.align(Alignment.Start))
            )

            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                value = textValue5,
                onValueChange = { newValue ->
                    textValue5 = newValue
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

            Spacer(modifier = Modifier.height(10.dp))

            Row {
                Button(onClick = {
                    if (textValue3.isEmpty() || textValue4.isEmpty() || textValue5.isEmpty() || textValue6.isEmpty()) {
                        mensajeError = "Todos los campos deben ser completados."
                        registroExitoso = false
                    } else {

                        GlobalScope.launch {
                            var idPredio = obtenerIdPredioSeleccionadoDep(nombrePredioSeleccionado) ?: 0
                            var estadoCasa = obtenerEstadoDep(nombreEstadoCasaSeleccionado)?: 0

                            val numeroCasa = textValue3.toShortOrNull() ?: 0
                            val pisoCasa = textValue4.toShortOrNull() ?: 0
                            val areaCasa = textValue5.toBigDecimalOrNull() ?: BigDecimal.ZERO
                            val participacionCasa = textValue6.toBigDecimalOrNull() ?: BigDecimal.ZERO

                            // verificamos que datos se guardaran en la base de datos
                            Log.d("Data", "$idPredio")
                            Log.d("Data", "$estadoCasa")
                            Log.d("Data", "$numeroCasa")
                            Log.d("Data", "$pisoCasa")
                            Log.d("Data", "$areaCasa")
                            Log.d("Data", "$participacionCasa")

                            //Crear el objeto AreaComun con los valores
                            val casa = PredioCasa(idPredio, estadoCasa, numeroCasa, pisoCasa, areaCasa, participacionCasa)

                            //Guardar el área común utilizando el repositorio
                            CasaRepository.guardarDep(casa)
                            registroExitoso = true
                            if(registroExitoso){
                                textValue3 = ""
                                textValue4 = ""
                                textValue5 = ""
                                textValue6 = ""
                            }
                        }
                    }
                },colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White), modifier = Modifier
                    .width(150.dp)
                    .height(40.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 16.dp))
                    .fillMaxWidth()
                ){
                    Text("Registrar", color = Black, fontSize = 15.sp)
                }
                Spacer(modifier = Modifier.width(35.dp))
                Button(onClick = {
                    navController.navigate(route= AppScreens.PantallaInicio.route)
                },colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White), modifier = Modifier
                    .width(150.dp)
                    .height(40.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 16.dp))
                    .fillMaxWidth()
                ){
                    Text("Cancelar", color = Black, fontSize = 15.sp)
                }
            }

            if (mensajeError.isNotEmpty()) {
                Text(
                    text = mensajeError,
                    color = Color.Red,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            } else if (registroExitoso) {
                Text(
                    text = "¡Registro exitoso!",
                    color = Color.Green,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FiltroSeleccionPredioDepa(): String{
    var textValue by remember { mutableStateOf(TextFieldValue("")) }
    var selectedName by remember { mutableStateOf("") }

    // Obtener la lista de nombres de predios desde la base de datos
    val names = remember {
        mutableStateListOf<String>()
    }

    // Llenar la lista de nombres de predios al iniciar la composición
    LaunchedEffect(key1 = true) {
        val predios = PredioRepository.obtenerNombresDePredios()
        names.clear()
        names.addAll(predios)
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
            LazyColumn (modifier = Modifier.width(350.dp).background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp))){
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
    return selectedName
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FiltroEstadoDepa(): String {
    var textValue by remember { mutableStateOf(TextFieldValue("")) }
    var selectedName by remember { mutableStateOf("") }

    val names = remember {
        mutableStateListOf<String>()
    }

    LaunchedEffect(key1 = true) {
        val casa = CasaRepository.obtenerEstadoCasa()
        names.clear()
        names.addAll(casa)
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
    return selectedName
}

@Preview(showBackground = true)
@Composable
fun RegistrarDepaPreview(){
    val navController = rememberNavController()
    RegistrarDepa(navController)
}

suspend fun obtenerIdPredioSeleccionadoDep(nombrePredio: String): Int? {
    return PredioRepository.obtenerIdPredioPorNombre(nombrePredio)
}

suspend fun obtenerEstadoDep(nombreEstadoCasa: String): Int? {
    return CasaRepository.obtenerIdEstadoPorNombre(nombreEstadoCasa)
}