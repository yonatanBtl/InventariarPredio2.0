package com.example.inventariarpredio20.screens

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.inventariarpredio20.R
import com.example.inventariarpredio20.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PantallaInicio(navController: NavController){
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
        Contenido(navController)
    }
}

@Composable
fun Contenido(navController: NavController){
    Box(modifier = Modifier.padding(start = 16.dp, top = 76.dp, end = 16.dp, bottom = 16.dp)){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue, shape = RoundedCornerShape(size = 16.dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Bienvenido",
                color = Color.White,
                fontSize = 25.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp)
                    .then(Modifier.align(Alignment.Start))
            )
            Spacer(modifier = Modifier.height(25.dp))
            Text("¿Qué operación desea realizar?",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp)
                    .then(Modifier.align(Alignment.Start))
            )

            Spacer(modifier = Modifier.height(20.dp))
            /** boton Registrar UVM**/
            Button(onClick = {
                navController.navigate(route = AppScreens.RegistrarUVM.route)
            }, colors = ButtonDefaults.buttonColors(
                containerColor = Color.White), modifier = Modifier
                .width(300.dp)
                .height(100.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(8.dp)
                )
                .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp))
            ){
                Row {
                    Image(painterResource(id = R.drawable.uvm__1_), "UVM")
                    Spacer(modifier = Modifier.width(16.dp))
                    Text("Registrar Unidad de Vivienda Múltiple", color = Color.Black, fontSize = 17.sp, modifier = Modifier.padding(top=8.dp))
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
            /** boton Registrar Area Comun**/
            Button(onClick = {
                navController.navigate(route = AppScreens.RegistrarAreaComun.route)
            },colors = ButtonDefaults.buttonColors(
                containerColor = Color.White), modifier = Modifier
                .width(300.dp)
                .height(100.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(8.dp)
                )
                .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp))
            ){
                Row {
                    Image(painterResource(id = R.drawable.area_comun), "UVM")
                    Spacer(modifier = Modifier.width(16.dp))
                    Text("Registrar Área Común del Predio", color = Color.Black, fontSize = 17.sp, modifier = Modifier.padding(top=16.dp))
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
            /** boton Registrar Depa**/
            Button(onClick = {
                navController.navigate(route = AppScreens.RegistrarDepa.route)
            },colors = ButtonDefaults.buttonColors(
                containerColor = Color.White), modifier = Modifier
                .width(300.dp)
                .height(100.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(8.dp)
                )
                .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp))
            ){
                Row {
                    Image(painterResource(id = R.drawable.depa), "UVM")
                    Spacer(modifier = Modifier.width(16.dp))
                    Text("Registrar Departamento / Casa", color = Color.Black, fontSize = 17.sp, modifier = Modifier.padding(top=8.dp))
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text("Consultar",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp)
                    .then(Modifier.align(Alignment.Start))
            )

            Spacer(modifier = Modifier.height(20.dp))
            /** boton Inventario**/
            Button(onClick = {
                navController.navigate(route = AppScreens.InventarioPredios.route)

            },colors = ButtonDefaults.buttonColors(
                containerColor = Color.White), modifier = Modifier
                .width(300.dp)
                .height(100.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(8.dp)
                )
                .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp))
            ){
                Row {
                    Image(painterResource(id = R.drawable.inventario), "UVM")
                    Spacer(modifier = Modifier.width(16.dp))
                    Text("Inventario de los Predios", color = Color.Black, fontSize = 17.sp, modifier = Modifier.padding(top=16.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaInicioPreview(){
    val navController = rememberNavController()
    PantallaInicio(navController)
}